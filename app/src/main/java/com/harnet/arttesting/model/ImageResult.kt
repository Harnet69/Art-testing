package com.harnet.arttesting.model

import com.google.gson.annotations.SerializedName

data class ImageResult(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    val id: Int,
    val imageHeight: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewWidth: Int,
    val previewURL: String,
    val tags: String,
    val type: String,
    val user: String,
    @SerializedName("user_id")
    val userId: Int,
    val userImageURL: String,
    val views: Int,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val webformatURL: String
)