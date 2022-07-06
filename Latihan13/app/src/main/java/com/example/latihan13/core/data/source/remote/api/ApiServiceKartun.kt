package com.example.latihan13.core.data.source.remote.api

import com.example.latihan13.core.data.source.remote.response.kartun.ResponseKartun
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceKartun {
    @GET("character")
    fun getKartun(): Call<ResponseKartun>
}