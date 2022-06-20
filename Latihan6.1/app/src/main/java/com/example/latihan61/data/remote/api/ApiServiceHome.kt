package com.example.latihan61.data.remote.api

import com.example.latihan61.data.remote.response.home.ResponseHomeItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceHome {
    @GET("users")
    fun getUsers(): Call<List<ResponseHomeItem>>
}