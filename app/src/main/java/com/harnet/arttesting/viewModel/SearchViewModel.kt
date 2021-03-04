package com.harnet.arttesting.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harnet.arttesting.model.ImageResponse
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.util.Resource

class SearchViewModel @ViewModelInject constructor(
    private val repository: ArtRepositoryInterface
) : ViewModel() {

    var searchedImages = MutableLiveData<Resource<ImageResponse>>()
    val selectedImageUrl = MutableLiveData<String>()

    //TODO can not working, because of the lack of coroutine
    private suspend fun searchImages(imageString: String) {
        searchedImages.value = repository.searchImg(imageString)
    }

    //TODO select the image from recyclerView Adapter
    fun selectImage() {

    }

}