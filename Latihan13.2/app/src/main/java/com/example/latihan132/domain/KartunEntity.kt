package com.example.latihan132.domain

data class KartunEntity(
    val id: Int,
    val image: String,
    val species: String,
    var name: String,
    val status: String,
    val originName: String,
    val created: String,
    val favorite: Boolean = false
)