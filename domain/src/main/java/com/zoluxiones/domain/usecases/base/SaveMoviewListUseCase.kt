package com.zoluxiones.domain.usecases.base

import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure
import com.zoluxiones.domain.repository.Repository
import com.zoluxiones.domain.usecases.base.SaveMoviewListUseCase.Params

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
class SaveMoviewListUseCase(private val repository: Repository) :
    BaseUseCase<Any, Params>() {
    override suspend fun run(params: Params): Either<Failure, Any> {
        return repository.getMoviesByPage(params.pageNumber)
    }

    data class Params(
        val pageNumber: Int
    )

}


