package com.harnet.arttesting.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    val name: String,
    val author: String,
    val imageUrl: String,
    val year: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)