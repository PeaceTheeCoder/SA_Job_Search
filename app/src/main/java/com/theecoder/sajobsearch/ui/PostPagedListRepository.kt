package com.theecoder.arkwork.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.theecoder.arkwork.data.api.POST_FAR_PAGE
import com.theecoder.arkwork.data.api.PostDBInterface
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.repository.PostDataSource
import com.theecoder.arkwork.data.repository.PostDataSource2
import com.theecoder.arkwork.data.repository.PostDataSourceFactory
import com.theecoder.arkwork.data.repository.PostDataSourceFactory2
import com.theecoder.arkwork.data.vo.Post
import io.reactivex.disposables.CompositeDisposable

class PostPagedListRepository (
    private val apiService: PostDBInterface,
    private val pined:Boolean?,
    private val category:Int?,
    private val order:String?,
    private val search:String?
    ){
    lateinit var postPageList: LiveData<PagedList<Post>>
    lateinit var postDataSourceFactory : PostDataSourceFactory

    fun fetchLivePostPagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<Post>>{
       postDataSourceFactory = PostDataSourceFactory(apiService, compositeDisposable,pined,category,order,search)
       val config:PagedList.Config = PagedList.Config.Builder()
           .setEnablePlaceholders(false)
           .setPageSize(POST_FAR_PAGE)
           .build()

        postPageList = LivePagedListBuilder(postDataSourceFactory, config).build()
        return postPageList
    }

    fun getNetworkState(): LiveData<NetworkState>{
        return  Transformations.switchMap<PostDataSource, NetworkState>(
            postDataSourceFactory.postLiveDataSource, PostDataSource::netwokstate
        )
    }
}

class PostPagedListRepository2 (
    private val apiService: PostDBInterface,
    private val pined:Boolean?,
    private val category:Int?,
    private val order:String?,
    private val search:String?
){
    lateinit var postPageList: LiveData<PagedList<Post>>
    lateinit var postDataSourceFactory : PostDataSourceFactory2

    fun fetchLivePostPagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<Post>>{
        postDataSourceFactory = PostDataSourceFactory2(apiService, compositeDisposable,pined,category,order,search)
        val config:PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_FAR_PAGE)
            .build()

        postPageList = LivePagedListBuilder(postDataSourceFactory, config).build()
        return postPageList
    }

    fun getNetworkState(): LiveData<NetworkState>{
        return  Transformations.switchMap<PostDataSource2, NetworkState>(
            postDataSourceFactory.postLiveDataSource, PostDataSource2::netwokstate
        )
    }
}