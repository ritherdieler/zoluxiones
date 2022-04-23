package com.zoluxiones.data.repository

import com.zoluxiones.data.mapper.MoviesMapper
import com.zoluxiones.data.network.datasources.LocalDataSource
import com.zoluxiones.data.network.datasources.RemoteDataSource
import com.zoluxiones.data.util.ConnectionUtils
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure
import com.zoluxiones.domain.model.DataOriginWrapper
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
    private val connectionUtils: ConnectionUtils
) : Repository {

    override suspend fun getMoviesByPage(pageNumber: Int): Either<Failure, DataOriginWrapper<List<Movie>>> {
        return if (connectionUtils.isNetworkAvailable()) {
            when (val response = remoteDataSource.getMoviesByPage(pageNumber)) {
                is Either.Success -> {
                    localDataSource.saveMovies(mapper.ToDatabaseList(response.data))
                    Either.Success(
                        DataOriginWrapper(
                            totaPages = response.data.total_pages,
                            comesFromRemote = true,
                            data = mapper.ToDomainList(response.data)
                        )
                    )
                }
                is Either.Error -> Either.Error(response.error)
            }
        } else {
            Either.Success(
                DataOriginWrapper(
                    comesFromRemote = false,
                    data = mapper.ToDomainList(localDataSource.getMovies()),
                    totaPages = 1
                )
            )
        }
    }

}

