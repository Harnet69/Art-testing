package com.harnet.arttesting.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnet.arttesting.model.ImageResponse
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.util.Resource
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val repository: ArtRepositoryInterface
) : ViewModel() {
    var searchedImages = MutableLiveData<Resource<ImageResponse>>()
    private val selectedImageUrl = MutableLiveData<String>()

    //TODO can not working, because of the lack of coroutine
    private fun searchImages(imageString: String) {
        if (imageString.isEmpty()){
            return
        }
        // loading image state
        searchedImages.value = Resource.loading(null)
        //when image was loaded
        viewModelScope.launch {
            val response = repository.searchImg(imageString)
            searchedImages.value = response
        }
    }

    //TODO select the image from recyclerView Adapter
    fun selectImage(imgUrl: String) {
        selectedImageUrl.value = imgUrl
    }

}