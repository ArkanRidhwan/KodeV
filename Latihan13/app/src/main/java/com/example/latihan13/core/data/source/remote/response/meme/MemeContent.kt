package com.example.latihan13.core.data.source.remote.response.meme

data class MemeContent(
    val box_count: Int,
    val height: Int,
    val id: String? = null,
    val name: String? = null,
    val url: String? = null,
    val width: Int
)