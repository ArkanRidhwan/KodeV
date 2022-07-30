package com.example.latihan13.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  Game(
    var id: Int,
    var name: String,
    var released: String,
    var background_image: String,
    var rating: String,
    var platforms: String,
    var genres: String,
    var minimum: String,
    var recommended: String,
    var favorite: Boolean = false,
) : Parcelable
