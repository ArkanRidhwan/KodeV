package com.kodev.games.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseGame(
    var results: List<DataGame>
): Parcelable
