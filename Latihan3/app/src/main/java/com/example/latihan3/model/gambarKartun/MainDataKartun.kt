package com.example.latihan3.model.gambarKartun

data class MainDataKartun(
    val info: Info,
    val results: List<MainDataKartun>,

    val image: String,
    val name: String,
    val origin: Origin,
    val status: String,
    val species: String,
    val created: String,
)