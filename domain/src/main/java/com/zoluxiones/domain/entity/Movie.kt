package com.zoluxiones.domain.entity


data class Movie(
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val release_date: String,
    val overview: String,
)
