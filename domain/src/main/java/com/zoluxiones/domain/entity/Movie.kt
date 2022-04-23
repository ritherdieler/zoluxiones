package com.zoluxiones.domain.entity

import java.io.Serializable

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
data class Movie(
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val release_date: String,
    val overview: String,
):Serializable