package com.example.latihan13.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_kartun")
data class KartunEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "origin")
    val originName: String,

    @ColumnInfo(name = "created")
    val created: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
)