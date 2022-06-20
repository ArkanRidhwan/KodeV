package com.example.latihan11.data.source.remote.api

import com.example.latihan11.data.source.remote.response.game.ResponseGame
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceGame {
    @GET("games")
    fun getGames(
        @Query("key") key: String,
        @Query("page_size") pageSize: String
    ): Call<ResponseGame>
}