package com.harnet.arttesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harnet.arttesting.model.ImageResponse
import com.harnet.arttesting.roomDb.Art
import com.harnet.arttesting.util.Resource

class FakeArtRepositoryAndroidTest: ArtRepositoryInterface {
    private val arts = arrayListOf<Art>()

    private val artsLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getAllArts(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImg(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }

    // fill live data with a predefine data
    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}