package com.example.latihan131.domain.model

data class Kartun(
    val id: Int,
    val image: String,
    val species: String,
    var name: String,
    val status: String,
    val originName: String,
    val created: String,
    val favorite: Boolean = false
)