package com.theecoder.arkwork.data.api.repository

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}
class NetworkState (val status: Status, val msg: String){

    companion object{
        val LOADED :NetworkState = NetworkState(Status.SUCCESS,"Success")
        val LOADING :NetworkState = NetworkState(Status.RUNNING,"Running")
        val ERROR :NetworkState = NetworkState(Status.FAILED,"Something went wrong!")
        val EOL : NetworkState = NetworkState(Status.FAILED,"No more Posts.")
    }
}