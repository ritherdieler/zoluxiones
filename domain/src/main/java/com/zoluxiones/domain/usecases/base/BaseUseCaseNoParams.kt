package com.zoluxiones.domain.usecases.base

import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure
import kotlinx.coroutines.*

abstract class BaseUseCaseNoParams<out Type> where Type : Any? {

    abstract suspend fun run(): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            onResult(
                Either.Error(Failure.MAPPER_ERROR)
            )
        }
        val backgroundJob = scope.async(Dispatchers.IO) { run() }
        scope.launch(exceptionHandler) { onResult(backgroundJob.await()) }
    }
}