package com.zoluxiones.domain.usecases.base

import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure
import kotlinx.coroutines.*

/**
 * By convention each [BaseUseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class BaseUseCase<Type, in Params> where Type : Any? {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        coroutineScope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {

        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            onResult(
                Either.Error(Failure.MAPPER_ERROR)
            )
        }
        val backgroundJob = coroutineScope.async(Dispatchers.IO) { run(params) }
        coroutineScope.launch(exceptionHandler) { onResult(backgroundJob.await()) }
    }
}