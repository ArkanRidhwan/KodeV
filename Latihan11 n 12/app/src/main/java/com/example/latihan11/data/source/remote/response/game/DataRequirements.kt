package com.example.latihan11.data.source.remote.response.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataRequirements(
    val minimum: String? = null,
    val recommended: String? = null
): Parcelable