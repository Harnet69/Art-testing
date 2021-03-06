package com.harnet.arttesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.harnet.arttesting.R
import com.harnet.arttesting.adapter.SearchAdapter
import com.harnet.arttesting.databinding.SearchFragmentBinding
import com.harnet.arttesting.util.FragmentBindingProvider
import com.harnet.arttesting.util.Status
import com.harnet.arttesting.viewModel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment @Inject constructor(
    private val searchRecyclerAdapter: SearchAdapter
) : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private val binding: SearchFragmentBinding by FragmentBindingProvider(R.layout.search_fragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        binding.foundArtsListRecyclerView.adapter = searchRecyclerAdapter
        //TODO check if it Linear Layout? Three images will be shown side-by-side
        binding.foundArtsListRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        //set listener to an recycler view item
        searchRecyclerAdapter.setOnItemClickListener {imgUrl ->
            //TODO is it necessary to return there?
            findNavController().popBackStack()
            viewModel.selectImage(imgUrl)
        }

        //delaying of user input
        var job: Job? = null

        // listen to user typing
        binding.searchedArt.addTextChangedListener {
            //if user start typing
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let{
                    if(it.toString().isNotEmpty()){
                        viewModel.searchImages(binding.searchedArt.text.toString())
                    }
                }
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.mSearchedImages.observe(viewLifecycleOwner, {status ->
            when(status.status){
                Status.SUCCESS -> {
                    //get a data as a list by HITS and map it to list of urls!!!
                    val urls = status.data?.hits?.map { imageResult -> imageResult.previewURL }
                    // send ulrs list to adapter
                    searchRecyclerAdapter.images = urls ?: listOf()

                    binding.searchProgressBar.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.searchProgressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    binding.searchProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

}