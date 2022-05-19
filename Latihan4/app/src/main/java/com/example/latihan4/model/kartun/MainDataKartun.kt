package com.example.latihan4.model.kartun

data class MainDataKartun(
    val results: List<MainDataKartun>,
    val image: String,
    val name: String,
    val origin: Origin,
    val status: String,
    val species: String,
    val created: String,
)