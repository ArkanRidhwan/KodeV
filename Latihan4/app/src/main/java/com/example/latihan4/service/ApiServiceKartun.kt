package com.example.latihan4.service

import com.example.latihan4.model.kartun.MainDataKartun
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceKartun {
    @GET("character")
    fun getKartun(): Call<MainDataKartun>
}