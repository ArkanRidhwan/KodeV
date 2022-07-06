package com.example.latihan13.core.domain.model

import java.util.*

data class Nasa(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val hdurl: String,
    val explanation: String,
    val date: String,
    val copyright: String,
    var favorite: Boolean = false
)
