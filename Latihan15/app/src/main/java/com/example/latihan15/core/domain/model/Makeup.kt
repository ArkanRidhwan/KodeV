package com.example.latihan15.core.domain.model

data class Makeup(
    var id: Int,
    var name: String,
    var price: String,
    var image: String,
    var description: String,
    var rating: Double,
    var hexValue: String,
    var colourName: String
)