package com.example.latihan4.service

import com.example.latihan4.model.nasa.MainDataNasaItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNasa {
    @GET("apod")
    fun getNasa(
        @Query("api_key") key: String,
        @Query("count") count: String
    ): Call<List<MainDataNasaItem>>
}