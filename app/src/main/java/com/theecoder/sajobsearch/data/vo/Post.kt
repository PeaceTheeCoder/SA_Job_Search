package com.theecoder.arkwork.data.vo


import com.google.gson.annotations.SerializedName

data class Post(
    val id: Int,
    val title: String,
    val category: Int,
    val imageUrl: String,
    val created: String,
    val location: String

)