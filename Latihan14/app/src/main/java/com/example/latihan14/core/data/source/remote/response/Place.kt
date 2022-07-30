package com.example.latihan14.core.data.source.remote.response

data class Place(
    val id: Int,
    val address: String,
    val description: String,
    val image: String,
    val latitude: Double,
    val like: Int,
    val longitude: Double,
    val name: String
)