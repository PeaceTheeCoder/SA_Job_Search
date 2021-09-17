package com.theecoder.arkwork.data.vo


import com.google.gson.annotations.SerializedName

data class PostDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("category")
    val category: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("salary")
    val salary: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("closing")
    val closing: String,
    @SerializedName("pined")
    val pined: Boolean
)