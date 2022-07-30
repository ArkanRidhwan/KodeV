package com.example.latihan131.data.datasource.remote.response.kartun

import com.example.latihan131.data.datasource.remote.response.kartun.Location
import com.example.latihan131.data.datasource.remote.response.kartun.Origin

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)