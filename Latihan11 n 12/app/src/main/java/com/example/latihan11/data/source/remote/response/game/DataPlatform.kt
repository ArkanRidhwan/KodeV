package com.example.latihan11.data.source.remote.response.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPlatform(
    val platform: DataPlatformItem,
    val released_at: String,
    val requirements_en: DataRequirements? = null,
): Parcelable