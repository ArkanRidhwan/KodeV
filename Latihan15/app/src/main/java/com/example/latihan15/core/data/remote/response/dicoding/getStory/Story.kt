package com.example.latihan15.core.data.remote.response.dicoding.getStory

data class Story(
    val createdAt: String,
    val description: String,
    val id: String,
    val lat: Any,
    val lon: Any,
    val name: String,
    val photoUrl: String
)