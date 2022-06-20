package com.example.latihan6.data.source.remote.api

import com.kodev.games.data.source.remote.response.ResponseGame
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceGame {
    @GET("games")
    fun getGames(
        @Query("key") key: String,
        @Query("page_size") pageSize: String,
    ): Call<ResponseGame>
}