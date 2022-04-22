package com.zoluxiones.domain.usecases.base

import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure
import com.zoluxiones.domain.repository.Repository
import com.zoluxiones.domain.usecases.base.GetMoviesByPageUseCase.Params

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
class GetMoviesByPageUseCase(private val repository: Repository) :
    BaseUseCase<List<Movie>, Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Movie>> {
        return repository.getMoviesByPage(params.pageNumber)
    }

    data class Params(
        val pageNumber: Int
    )

}


