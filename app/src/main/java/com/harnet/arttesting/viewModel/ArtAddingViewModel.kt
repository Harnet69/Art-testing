package com.harnet.arttesting.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.room.Art
import com.harnet.arttesting.util.Resource
import kotlinx.coroutines.launch

class ArtAddingViewModel @ViewModelInject constructor(private val repository: ArtRepositoryInterface) : ViewModel() {
    //TODO keeping the state of image loading?
    var insertArtMsg = MutableLiveData<Resource<Art>>()
    val selectedImg = MutableLiveData<String>()

    //TODO implement a coroutine scope here
    fun insertImageToDb(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    // when the art was successfully added to a database
    fun resetInsertArtMessage(){
        insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    // when a user clicks on an image
    fun setSelectedImg(url: String){
        selectedImg.value = url
    }

    fun deleteArt(art: Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }
}