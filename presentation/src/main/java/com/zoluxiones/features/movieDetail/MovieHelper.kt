package com.zoluxiones.features.movieDetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MovieHelper(
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val release_date: String,
    val overview: String,
): Parcelable