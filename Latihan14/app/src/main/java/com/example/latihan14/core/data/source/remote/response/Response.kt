package com.example.latihan14.core.data.source.remote.response

data class Response(
    val count: Int,
    val error: Boolean,
    val message: String,
    val places: List<Place>
)