package com.theecoder.arkwork.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.theecoder.arkwork.data.api.PostDBInterface
import com.theecoder.arkwork.data.vo.Post
import io.reactivex.disposables.CompositeDisposable

class PostDataSourceFactory (
    private val apiService : PostDBInterface,
    private val compositeDisposable: CompositeDisposable,
    private val pined:Boolean?,
    private val category:Int?,
    private val order:String?,
    private val search:String?
):
    DataSource.Factory<Int, Post>() {

    val postLiveDataSource = MutableLiveData<PostDataSource>()
    override fun create(): DataSource<Int, Post> {
       val postDataSource = PostDataSource(apiService, compositeDisposable,pined, category, order,search)

        postLiveDataSource.postValue(postDataSource)
        return postDataSource
    }
}

class PostDataSourceFactory2 (
    private val apiService : PostDBInterface,
    private val compositeDisposable: CompositeDisposable,
    private val pined:Boolean?,
    private val category:Int?,
    private val order:String?,
    private val search:String?
):
    DataSource.Factory<Int, Post>() {

    val postLiveDataSource = MutableLiveData<PostDataSource2>()
    override fun create(): DataSource<Int, Post> {
        val postDataSource = PostDataSource2(apiService, compositeDisposable,pined, category, order,search)

        postLiveDataSource.postValue(postDataSource)
        return postDataSource
    }
}