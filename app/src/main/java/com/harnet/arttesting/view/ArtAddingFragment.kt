package com.harnet.arttesting.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.RequestManager
import com.harnet.arttesting.R
import com.harnet.arttesting.databinding.ArtAddingFragmentBinding
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
                goToArtSearch(it.artImageArtAdding)
            }
        }
    }

    private fun goToArtSearch(img: ImageView) {
        img.setOnClickListener {
            Navigation.findNavController(img)
                .navigate(ArtAddingFragmentDirections.actionArtAddingFragmentToSearchFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

}