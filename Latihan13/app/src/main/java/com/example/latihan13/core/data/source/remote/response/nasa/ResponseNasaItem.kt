package com.example.latihan13.core.data.source.remote.response.nasa

data class ResponseNasaItem(
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    val hdurl: String? = null,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)