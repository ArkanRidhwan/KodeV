package com.example.latihan121.data.source.remote.api

import com.example.latihan121.data.source.remote.response.game.ResponseGame
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceGame {
    @GET("games")
    fun getGames(
        @Query("key") key: String
    ): Call<ResponseGame>
}