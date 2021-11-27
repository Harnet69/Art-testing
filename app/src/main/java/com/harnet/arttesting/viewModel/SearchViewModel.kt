package com.harnet.arttesting.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnet.arttesting.model.ImageResponse
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ArtRepositoryInterface
) : ViewModel() {
    var mSearchedImages = MutableLiveData<Resource<ImageResponse>>()
    val mSelectedImageUrl = MutableLiveData<String>()

    //TODO can not working, because of the lack of coroutine
    fun searchImages(imageString: String) {
        if (imageString.isEmpty()){
            return
        }
        // loading image state
        mSearchedImages.value = Resource.loading(null)
        //when image was loaded
        viewModelScope.launch {
            val response = repository.searchImg(imageString)
            mSearchedImages.value = response
        }
    }

    //TODO select the image from recyclerView Adapter
    fun selectImage(imgUrl: String) {
        mSelectedImageUrl.value = imgUrl
    }

}