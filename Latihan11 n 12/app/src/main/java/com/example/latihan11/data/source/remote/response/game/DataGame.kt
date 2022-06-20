package com.example.latihan11.data.source.remote.response.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataGame(
    val background_image: String,
    val genres: List<DataGenre>,
    val id: Int,
    val name: String,
    val platforms: List<DataPlatform>,
    val rating: Double,
    val released: String,
) : Parcelable