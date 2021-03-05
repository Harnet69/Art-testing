package com.harnet.arttesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.harnet.arttesting.R
import com.harnet.arttesting.adapter.ArtRecyclerAdapter
import com.harnet.arttesting.databinding.SearchFragmentBinding
import com.harnet.arttesting.util.FragmentBindingProvider
import com.harnet.arttesting.viewModel.SearchViewModel
import javax.inject.Inject

class SearchFragment @Inject constructor(
    searchRecyclerAdapter: ArtRecyclerAdapter
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
    }

}