package com.harnet.arttesting.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: Art)

    @Delete
    suspend fun deleteArt(art: Art)

    //not suspend because it returns an asynchronous LiveData
    @Query("SELECT * FROM arts")
    fun getAllArts(): LiveData<List<Art>>

}