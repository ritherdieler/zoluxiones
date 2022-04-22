package com.zoluxiones.data.network.datasources

import com.zoluxiones.data.database.dao.MovieDao
import com.zoluxiones.data.database.model.Movie

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
class LocalDataSourceImpl(private val movieDao: MovieDao) : LocalDataSource {
    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.savePlaces(movies)
    }

    override suspend fun getMovies(): List<Movie> {
       return movieDao.getAllPlaces()
    }
}