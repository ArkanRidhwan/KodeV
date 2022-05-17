package com.example.latihan2.model

data class PlatformX(
    val platform: PlatformXX,
    val released_at: String,
    val requirements_en: RequirementsEn? = null,
    val requirements_ru: Any? = null
)