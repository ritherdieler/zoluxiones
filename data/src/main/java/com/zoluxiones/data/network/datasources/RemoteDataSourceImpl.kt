package com.zoluxiones.data.network.datasources

import com.zoluxiones.data.BuildConfig
import com.zoluxiones.data.network.api.MoviesAPI
import com.zoluxiones.data.network.response.MoviesResponse
import com.zoluxiones.domain.model.Either
import com.zoluxiones.data.util.NetworkUtils
import com.zoluxiones.domain.model.Failure

class RemoteDataSourceImpl(
    private val networkUtils: NetworkUtils,
    private val moviesAPI: MoviesAPI
) : RemoteDataSource {

    override suspend fun getMoviesByPage(pageNumber: Int): Either<Failure, MoviesResponse> {
        return networkUtils.callFromRemote { moviesAPI.getMoviesByPage(pageNumber,BuildConfig.KEY) }
    }

}