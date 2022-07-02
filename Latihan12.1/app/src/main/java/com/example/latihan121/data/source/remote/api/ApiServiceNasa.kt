package com.example.latihan121.data.source.remote.api

import com.example.latihan121.data.source.remote.response.nasa.ResponseNasaItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNasa {
    @GET("apod")
    fun getNasa(
        @Query("api_key") key: String,
        @Query("count") total: Int,
    ): Call<List<ResponseNasaItem>>
}