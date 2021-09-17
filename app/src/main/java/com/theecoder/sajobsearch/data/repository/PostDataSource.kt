package com.theecoder.arkwork.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.theecoder.arkwork.data.api.FIRST_PAGE
import com.theecoder.arkwork.data.api.PostDBInterface
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.vo.Post
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostDataSource(private val apiService : PostDBInterface,
                     private val compositeDisposable: CompositeDisposable,
                     private val pined:Boolean?,
                     private val category:Int?,
                     private val order:String?,
                     private val search:String?
                     ):
    PageKeyedDataSource<Int, Post>() {

    private var page = FIRST_PAGE
    val netwokstate : MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        netwokstate.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPosts(params.key,pined,category,order,search)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if(it.next != null){
                        callback.onResult(it.postList, params.key+1)
                        netwokstate.postValue(NetworkState.LOADED)
                    }else if (it.next == null)
                    {
                        callback.onResult(it.postList, null)
                        netwokstate.postValue(NetworkState.EOL)
                    }
                    else{
                        netwokstate.postValue(NetworkState.ERROR)
                        Log.e("Post", it.count.toString())
                    }
                },{
                    netwokstate.postValue(NetworkState.ERROR)
                    Log.e("Post", it.message.toString())
                })
        )
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Post>,
    ) {
        netwokstate.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPosts(page,pined)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it.postList, null, page+1)
                    netwokstate.postValue(NetworkState.LOADED)
                },{
                    netwokstate.postValue(NetworkState.ERROR)
                    Log.e("Post", it.message.toString())
                })
        )
    }
}

class PostDataSource2(private val apiService : PostDBInterface,
                     private val compositeDisposable: CompositeDisposable,
                     private val pined:Boolean?,
                     private val category:Int?,
                     private val order:String?,
                     private val search:String?
):
    PageKeyedDataSource<Int, Post>() {

    private var page = FIRST_PAGE
    val netwokstate : MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        netwokstate.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPosts2(params.key,pined,category,order,search)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if(it.next != null){
                        callback.onResult(it.postList, params.key+1)
                        netwokstate.postValue(NetworkState.LOADED)
                    }else if (it.next == null)
                    {
                        callback.onResult(it.postList, null)
                        netwokstate.postValue(NetworkState.EOL)
                    }
                    else{
                        netwokstate.postValue(NetworkState.ERROR)
                        Log.e("Post", it.count.toString())
                    }
                },{
                    netwokstate.postValue(NetworkState.ERROR)
                    Log.e("Post", it.message.toString())
                })
        )
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Post>,
    ) {
        netwokstate.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPosts(page,pined)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it.postList, null, page+1)
                    netwokstate.postValue(NetworkState.LOADED)
                },{
                    netwokstate.postValue(NetworkState.ERROR)
                    Log.e("Post", it.message.toString())
                })
        )
    }
}