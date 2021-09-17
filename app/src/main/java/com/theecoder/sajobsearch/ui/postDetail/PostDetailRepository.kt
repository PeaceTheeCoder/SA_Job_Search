package com.theecoder.arkwork.ui.postDetail

import androidx.lifecycle.LiveData
import com.theecoder.arkwork.data.api.PostDBInterface
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.repository.PostDetailNetworkDataSource
import com.theecoder.arkwork.data.vo.PostDetails
import io.reactivex.disposables.CompositeDisposable

class PostDetailRepository(private val apiService : PostDBInterface) {

    lateinit var postDetailNetworkDataSource: PostDetailNetworkDataSource

    fun fetchPostDetail(compositeDisposable: CompositeDisposable, postId: Int): LiveData<PostDetails> {
        postDetailNetworkDataSource = PostDetailNetworkDataSource(apiService,compositeDisposable)
        postDetailNetworkDataSource.fetchPostDetail(postId)

        return postDetailNetworkDataSource.downloadedPostDetailResponse
    }

    fun getPostDetailNetworkState():LiveData<NetworkState>{
        return postDetailNetworkDataSource.networkState
    }

}