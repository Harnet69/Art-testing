package com.harnet.arttesting.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.harnet.arttesting.R
import com.harnet.arttesting.databinding.ArtAddingFragmentBinding
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.room.Art
import com.harnet.arttesting.util.Resource
import com.harnet.arttesting.viewModel.ArtAddingViewModel

class ArtAddingFragment @ViewModelInject constructor(private val repository: ArtRepositoryInterface) :
    Fragment(R.layout.art_adding_fragment) {
    private lateinit var viewModel: ArtAddingViewModel
    private var viewBinding: ArtAddingFragmentBinding? = null

    //TODO state of image loading?
    val insertArtMsg = MutableLiveData<Resource<Art>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtAddingViewModel::class.java)
        viewBinding = ArtAddingFragmentBinding.bind(view)

        viewBinding.let {
            if (it != null) {
                goToArtSearch(it.artImageArtAdding)
            }
        }
    }

    //TODO implement a coroutine scope here
    suspend fun insertImageToDb(art: Art) {
        repository.insertArt(art)
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