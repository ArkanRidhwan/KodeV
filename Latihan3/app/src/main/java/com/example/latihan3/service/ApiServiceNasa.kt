package com.example.latihan3.service

import com.example.latihan3.model.gambarNasa.MainDataNasaItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNasa {
    @GET("apod")
    fun getListNasa(
        @Query("api_key") key: String,
        @Query("count") count: String
    ): Call<List<MainDataNasaItem>>
}