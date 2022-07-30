package com.example.latihan14.core.data.source.remote.api

import com.example.latihan14.core.data.source.remote.response.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getDestinasi(): Response
}