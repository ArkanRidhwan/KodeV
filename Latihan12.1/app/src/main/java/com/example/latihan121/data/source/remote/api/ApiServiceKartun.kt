package com.example.latihan121.data.source.remote.api

import com.example.latihan121.data.source.remote.response.kartun.ResponseKartun
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceKartun {
    @GET("character")
    fun getKartun(): Call<ResponseKartun>
}