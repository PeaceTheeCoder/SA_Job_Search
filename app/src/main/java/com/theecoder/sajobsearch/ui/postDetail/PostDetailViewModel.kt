package com.theecoder.arkwork.ui.postDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.vo.PostDetails
import io.reactivex.disposables.CompositeDisposable

class PostDetailViewModel(private val postDetailRepository: PostDetailRepository, postID:Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val postDetails : LiveData<PostDetails> by lazy {
        postDetailRepository.fetchPostDetail(compositeDisposable, postID)
    }

    val networkState : LiveData<NetworkState> by lazy {
        postDetailRepository.getPostDetailNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}