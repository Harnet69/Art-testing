package com.harnet.arttesting.repository

import androidx.lifecycle.LiveData
import com.harnet.arttesting.model.ImageResponse
import com.harnet.arttesting.room.Art
import com.harnet.arttesting.util.Resource

interface ArtRepositoryInterface {
    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getAllArts(): LiveData<List<Art>>

    suspend fun searchImg(imageString: String): Resource<ImageResponse>
}