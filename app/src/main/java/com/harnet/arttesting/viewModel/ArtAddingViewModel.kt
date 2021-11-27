package com.harnet.arttesting.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.room.Art
import com.harnet.arttesting.util.Resource
import com.harnet.arttesting.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtAddingViewModel @Inject constructor(private val repository: ArtRepositoryInterface) :
    ViewModel() {
    //keeping the state of image loading?
    var mInsertArtMsg = MutableLiveData<Resource<Art>>()
    var selectedImgUrl = MutableLiveData<String>()

    // when the art was successfully added to a database
    fun resetInsertArtMessage() {
        mInsertArtMsg = MutableLiveData<Resource<Art>>()
        selectedImgUrl = MutableLiveData<String>()
    }

    // when a user clicks on an image
    fun setSelectedImg(url: String) {
        // postValue is very good for testing because it notifies observers immediately
        selectedImgUrl.postValue(url)
    }

    fun addArt(artName: String, artAuthor: String, artYear: String){
        addArtToDb(validateArt(artName, artAuthor, artYear))
    }

    private fun validateArt(artName: String, artAuthor: String, artYear: String): Resource<Art> {
        if (artName.isEmpty() || artAuthor.isEmpty() || artYear.isEmpty()) {
            return Resource.error("Field can't be empty", null)
        }
        val yearInt = try {
            artYear.toInt()
        } catch (e: Exception) {
            return Resource.error("Year should be a number", null)
        }
        //if selectedImgUrl isn't valid add empty string
        return Resource.success(Art(artName, artAuthor, selectedImgUrl.value ?: "", yearInt))

    }

    private fun addArtToDb(artStatus: Resource<Art>) {
        when (artStatus.status) {
            Status.SUCCESS -> {
                viewModelScope.launch {
                    artStatus.data?.let { repository.insertArt(it) }
                }
                mInsertArtMsg.postValue(Resource.success(artStatus.data))
            }
            Status.ERROR -> {
                mInsertArtMsg.postValue(Resource.error(artStatus.message.toString(), null))
            }
            else -> {
                mInsertArtMsg.postValue(Resource.error("Unknown msg", null))
            }
        }
    }

}