package com.example.latihan15.core.data.remote.response.dicoding.getStory

data class ResponseStory(
    val error: Boolean,
    val listStory: List<Story>,
    val message: String
)