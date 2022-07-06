package com.example.latihan13.core.data.source.remote.response.game

data class PlatformX(
    val platform: PlatformXX,
    val released_at: String,
    val requirements_en: RequirementsEn? = null,
    val requirements_ru: Any
)