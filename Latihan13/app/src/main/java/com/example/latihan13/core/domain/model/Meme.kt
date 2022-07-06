package com.example.latihan13.core.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Meme(
    var id: String,
    val name: String,
    val image: String
)