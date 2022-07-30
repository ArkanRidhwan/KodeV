package com.example.latihan14.core.data.source.local.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_destinasi")
data class DestinasiEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "like")
    var like: Int,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "name")
    var name: String,
)