package com.example.latihan11.data.source.remote.api

import com.example.latihan11.data.source.remote.response.kartun.ResponseKartun
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceKartun {
    @GET("character")
    fun getKartun(): Call<ResponseKartun>
}