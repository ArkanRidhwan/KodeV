package com.example.latihan3.service

import com.example.latihan3.model.gambarKartun.MainDataKartun
import retrofit2.Call
import retrofit2.http.GET

    interface ApiServiceKartun {
        @GET("character")
        fun getListCharacter(): Call<MainDataKartun>
    }