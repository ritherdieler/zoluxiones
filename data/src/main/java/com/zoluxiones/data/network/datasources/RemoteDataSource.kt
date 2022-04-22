package com.zoluxiones.data.network.datasources

import com.zoluxiones.data.network.response.MoviesResponse
import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
interface RemoteDataSource {
    suspend fun getMoviesByPage(pageNumber:Int): Either<Failure, MoviesResponse>
}