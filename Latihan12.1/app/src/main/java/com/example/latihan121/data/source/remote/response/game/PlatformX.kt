package com.example.latihan121.data.source.remote.response.game

data class PlatformX(
    val platform: PlatformXX,
    val released_at: String,
    val requirements_en: RequirementsEn? = null,
    val requirements_ru: Any
)