package com.theecoder.arkwork.data.repository

import android.util.Log
import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theecoder.arkwork.data.api.PostDBInterface
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.vo.PostDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostDetailNetworkDataSource(private val apiservice : PostDBInterface, private val compositeDisposable: CompositeDisposable){
    private val _networkstate = MutableLiveData<NetworkState>()
    val  networkState: LiveData<NetworkState>
        get() = _networkstate

    private val _downloadedPostDetailResponse = MutableLiveData<PostDetails>()
    val  downloadedPostDetailResponse: LiveData<PostDetails>
        get() = _downloadedPostDetailResponse

    fun fetchPostDetail(postid: Int){
        try{
            compositeDisposable.add(
                apiservice.getPostDetail(postid)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        _downloadedPostDetailResponse.postValue(it)
                        _networkstate.postValue(NetworkState.LOADED)
                    },{
                        _networkstate.postValue(NetworkState.ERROR)
                        Log.e("PostDetailSource", it.message.toString())
                    })
            )
        }catch (e :Exception){
            Log.e("PostDetailSource", e.message.toString())
        }
    }
}