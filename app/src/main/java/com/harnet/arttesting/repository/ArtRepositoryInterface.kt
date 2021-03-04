package com.harnet.arttesting.repository

import androidx.lifecycle.LiveData
import com.harnet.arttesting.model.ImageResponse
import com.harnet.arttesting.room.Art
import com.harnet.arttesting.util.Resource

// created by the testing reason. Allows to get a fake ArtRepository
interface ArtRepositoryInterface {
    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getAllArts(): LiveData<List<Art>>

    // assign success, error or loading state to ImageResponse
    suspend fun searchImg(imageString: String): Resource<ImageResponse>
}