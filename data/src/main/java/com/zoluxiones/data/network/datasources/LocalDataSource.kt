package com.zoluxiones.data.network.datasources

import com.zoluxiones.data.database.model.Movie

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
interface LocalDataSource {

    suspend fun saveMovies(movies: List<Movie>)

    suspend fun getMovies(): List<Movie>

}