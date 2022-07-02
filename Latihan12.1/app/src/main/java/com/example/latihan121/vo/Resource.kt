package com.example.latihan121.vo

data class Resource<T> (val status: Status, val data: T?, val message: String?){
    companion object{
        fun <T>success(data: T?) = Resource(Status.SUCESS, data, null)
        fun <T>error(msg: String?, data: T?) = Resource(Status.ERROR, data, msg)
        fun <T>loading(data: T?) = Resource(Status.LOADING, data, null)
    }
}