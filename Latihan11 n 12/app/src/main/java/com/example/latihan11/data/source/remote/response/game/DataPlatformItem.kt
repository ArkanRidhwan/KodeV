package com.example.latihan11.data.source.remote.response.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPlatformItem(
    val id: Int,
    val name: String,
): Parcelable