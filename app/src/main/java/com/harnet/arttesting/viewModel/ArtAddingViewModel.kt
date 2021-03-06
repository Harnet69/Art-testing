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
    //keeping the state of image loading?
    var mInsertArtMsg = MutableLiveData<Resource<Art>>()
    val selectedImgUrl = MutableLiveData<String>()

    private fun insertImageToDb(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    // when the art was successfully added to a database
    fun resetInsertArtMessage(){
        mInsertArtMsg = MutableLiveData<Resource<Art>>()
    }

    // when a user clicks on an image
    fun setSelectedImg(url: String){
        // postValue is very good for testing because it notifies observers immediately
        selectedImgUrl.postValue(url)
    }

    fun deleteArt(art: Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun validateUserInput(artName: String, artAuthor: String, artYear: String){
        if(artName.isEmpty() || artAuthor.isEmpty() || artYear.isEmpty()){
            mInsertArtMsg.postValue(Resource.error("Field can't be empty", null))
            return
        }
        val yearInt = try {
            artYear.toInt()
        }catch (e: Exception){
            mInsertArtMsg.postValue(Resource.error("Year should be a number", null))
            return
        }
        //if selectedImgUrl isn't valid add empty string
        val art = Art(artName, artAuthor, selectedImgUrl.value ?: "", yearInt)
        insertImageToDb(art)
        setSelectedImg("")
        mInsertArtMsg.postValue(Resource.success(art))
    }
}