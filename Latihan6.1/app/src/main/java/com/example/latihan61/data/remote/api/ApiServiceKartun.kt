package com.example.latihan61.data.remote.api

import com.example.latihan61.data.remote.response.kartun.KartunResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceKartun {
    @GET("character")
    fun getCharacters(): Call<KartunResponse>
}