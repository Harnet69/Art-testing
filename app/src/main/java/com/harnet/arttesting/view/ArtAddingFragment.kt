package com.harnet.arttesting.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.button.MaterialButton
import com.harnet.arttesting.R
import com.harnet.arttesting.databinding.ArtAddingFragmentBinding
import com.harnet.arttesting.util.Status
import com.harnet.arttesting.viewModel.ArtAddingViewModel
import javax.inject.Inject

class ArtAddingFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.art_adding_fragment) {
    private lateinit var viewModel: ArtAddingViewModel
    private var viewBinding: ArtAddingFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArtAddingViewModel::class.java)
        viewBinding = ArtAddingFragmentBinding.bind(view)

        viewBinding.let {
            if (it != null) {
                goToArtSearchListener(it.artImageArtAdding)
            }
        }

        //handling onBackPress dispatcher
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        // add the art to a database listener
        viewBinding?.artAddBtnArtAdding?.let { addArtListener(it) }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.selectedImgUrl.observe(viewLifecycleOwner, { imgUrl ->
            viewBinding?.let {
                glide.load(imgUrl).into(it.artImageArtAdding)
            }
        })

        // handling the state of an art loading
        viewModel.mInsertArtMsg.observe(viewLifecycleOwner, { state ->
            when (state.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
                    //TODO can be a problem
                    findNavController().popBackStack()
                    //reset status of loading to neutral
                    viewModel.resetInsertArtMessage()
                }
                Status.LOADING -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), state.message ?: "Unexpected load error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun goToArtSearchListener(imageView: ImageView) {
        imageView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(ArtAddingFragmentDirections.actionArtAddingFragmentToSearchFragment())
        }
    }

    private fun addArtListener(addBtn: MaterialButton) {
        addBtn.setOnClickListener {
            viewBinding?.let {
                viewModel.validateUserInput(
                    it.artNameArtAdding.text.toString(),
                    it.artAuthorArtAdding.text.toString(),
                    it.artYearArtAdding.text.toString()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

}