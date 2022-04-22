package com.zoluxiones.domain.repository

import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
interface Repository {
    suspend fun getMoviesByPage(pageNumber: Int): Either<Failure, List<Movie>>
}