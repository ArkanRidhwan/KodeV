package com.example.latihan2.model

data class DataGame(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String,
    val nofollow: Boolean,
    val nofollow_collections: List<String>,
    val noindex: Boolean,
    val previous: Any,
    val results: List<Result>,
    val seo_description: String,
    val seo_h1: String,
    val seo_keywords: String,
    val seo_title: String,

    val background_image: String,
    val id: Int,
    val name: String,
    val rating: Double,
    val rating_top: Int,
    val ratings_count: Int,
    val released: String,
    val genres: List<Genre>,
    val stores: List<Store>,
    val platforms: List<PlatformX>,

)