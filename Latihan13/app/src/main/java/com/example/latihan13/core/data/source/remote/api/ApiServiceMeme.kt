package com.example.latihan13.core.data.source.remote.api

import com.example.latihan13.core.data.source.remote.response.meme.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceMeme {
    @GET("get_memes")
    fun getMeme(): Call<Data>
}