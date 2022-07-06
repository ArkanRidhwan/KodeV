package com.example.latihan13.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tb_nasa")
data class NasaEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "hdurl")
    var hdurl: String,

    @ColumnInfo(name = "explanation")
    var explanation: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "copyright")
    var copyright: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
)