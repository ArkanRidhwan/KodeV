package com.example.latihan131.data.datasource.remote.api

import com.example.latihan131.data.datasource.remote.response.kartun.ResponseKartun
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceKartun {
    @GET("character")
    fun getKartun(): Call<ResponseKartun>
}