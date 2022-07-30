package com.example.latihan15.core.data.remote.api

import com.example.latihan15.core.data.remote.response.makeup.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceMakeUp {
    @GET("products.json")
    suspend fun getMakeup(
        @Query("brand") brand: String
    ): List<ResponseItem>
}