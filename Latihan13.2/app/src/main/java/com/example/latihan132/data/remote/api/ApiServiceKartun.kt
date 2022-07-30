package com.example.latihan132.data.remote.api

import com.example.latihan132.data.remote.response.ResponseKartun
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceKartun {
    @GET("character")
    fun getKartun(): Call<ResponseKartun>
}