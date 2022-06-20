package com.example.latihan5.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class DataPlatform(
    val platform: DataPlatformItem,
    val release_at: String,
    val requirements_en: DataRequirements? = null,
)
