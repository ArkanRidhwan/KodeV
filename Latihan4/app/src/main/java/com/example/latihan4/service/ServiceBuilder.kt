package com.example.latihan4.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceBuilder {
    val retrofitKartun = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiServiceKartun = retrofitKartun.create(ApiServiceKartun::class.java)

    val retrofitNasa = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/planetary/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiServiceNasa = retrofitNasa.create(ApiServiceNasa::class.java)
}