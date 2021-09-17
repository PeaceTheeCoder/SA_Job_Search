package com.theecoder.arkwork.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.vo.Post
import io.reactivex.disposables.CompositeDisposable


class MainActivityViewModel(private val postRepository: PostPagedListRepository):ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val postPagedList: LiveData<PagedList<Post>> by lazy {
        postRepository.fetchLivePostPagedList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        postRepository.getNetworkState()
    }


    fun listEmpty(): Boolean{
        return postPagedList.value?.isEmpty()?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

class MainActivityViewModel2(private val postRepository: PostPagedListRepository2):ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val postPagedList: LiveData<PagedList<Post>> by lazy {
        postRepository.fetchLivePostPagedList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        postRepository.getNetworkState()
    }

    fun listEmpty(): Boolean{
        return postPagedList.value?.isEmpty()?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}