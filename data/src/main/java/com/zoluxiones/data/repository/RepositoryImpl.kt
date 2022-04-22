package com.zoluxiones.data.repository

import com.zoluxiones.data.mapper.MoviesMapper
import com.zoluxiones.data.network.datasources.LocalDataSource
import com.zoluxiones.data.network.datasources.RemoteDataSource
import com.zoluxiones.data.network.response.MoviesResponse
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure
import com.zoluxiones.domain.repository.Repository

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: MoviesMapper,
) : Repository {

    override suspend fun getMoviesByPage(pageNumber: Int): Either<Failure, List<Movie>> {
        return when (val response = remoteDataSource.getMoviesByPage(pageNumber)) {
            is Either.Success -> saveAndReturnFromLocal(response.data)
            is Either.Error -> Either.Error(response.error)
        }
    }

    private suspend fun saveAndReturnFromLocal(data: MoviesResponse): Either<Failure, List<Movie>> {
        return try {
            localDataSource.saveMovies(mapper.ToDatabaseList(data))
            val dbMoviews = localDataSource.getMovies()
            Either.Success(mapper.ToDomainList(dbMoviews))
        } catch (e: Exception) {
            Either.Error(Failure.LOCAL_DB_ERROR)
        }
    }

}

