package com.theecoder.arkwork.data.vo

import com.google.gson.annotations.SerializedName


data class PostResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results")
    val postList: List<Post>
)