package com.example.latihan15.core.data.local.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_makeup")
data class MakeupEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "price")
    var price: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "hexValue")
    var hexValue: String,

    @ColumnInfo(name = "colourName")
    var colourName: String,

)