package com.example.latihan15.core.data.remote.api

import com.example.latihan15.core.data.remote.response.dicoding.getStory.ResponseStory
import com.example.latihan15.core.data.remote.response.dicoding.postStory.ResponsePostStory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiServiceStory {
    @GET("stories")
    suspend fun getStory(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") auth: String,
    ): ResponseStory

    @Multipart
    @POST("stories")
    suspend fun postStory(
        @Header("Authorization") auth: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): ResponsePostStory
}