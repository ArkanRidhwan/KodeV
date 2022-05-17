package com.example.latihan2.service

import com.example.latihan2.model.ResponGame
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    fun getListGame(
        @Query("key") key: String,
        @Query("page_size") pageSize: String,


    ): Call<ResponGame>
}