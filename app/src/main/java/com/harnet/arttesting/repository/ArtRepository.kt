package com.harnet.arttesting.repository

import androidx.lifecycle.LiveData
import com.harnet.arttesting.model.ImageResponse
import com.harnet.arttesting.retrofit.RetrofitAPI
import com.harnet.arttesting.room.Art
import com.harnet.arttesting.room.ArtDao
import com.harnet.arttesting.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
) : ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getAllArts(): LiveData<List<Art>> {
        return artDao.getAllArts()
    }

    override suspend fun searchImg(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.searchImg(imageString)
            if(response.isSuccessful){
                response.body().let {data ->
                    return@let Resource.success(data)
                } ?: Resource.error("Error", null)
            }else{
                Resource.error("Error", null)
            }
        } catch (e : Exception){
            Resource.error("No data", null)
        }
    }
}