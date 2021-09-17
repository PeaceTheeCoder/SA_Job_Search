package com.theecoder.arkwork.data.api

import com.theecoder.arkwork.data.vo.CategorResponseItem
import com.theecoder.arkwork.data.vo.PostDetails
import com.theecoder.arkwork.data.vo.PostResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostDBInterface {
    //https://arkwork.herokuapp.com/api/posts/{post_id}
    //https://arkwork.herokuapp.com/api/posts

    @GET("posts")
    fun getPosts(
        @Query("page") page : Int,
        @Query("pined") pined : Boolean?,
        @Query("category") category : Int? = null,
        @Query("ordering") order : String? = "-created",
        @Query("search") search : String? = null,
    ): Single<PostResponse>

    @GET("posts")
    fun getPosts2(
        @Query("page") page : Int,
        @Query("pined") pined : Boolean?,
        @Query("category") category : Int? = null,
        @Query("ordering") order : String? = "-created",
        @Query("search") search : String? = null,
    ): Single<PostResponse>

    @GET("posts/{post_id}")
    fun getPostDetail(@Path("post_id") id: Int): Single<PostDetails>

    @GET("categories/")
    fun getAllCategories(): Call<List<CategorResponseItem>>
}