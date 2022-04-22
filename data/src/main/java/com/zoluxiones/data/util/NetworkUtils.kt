package com.zoluxiones.data.util

import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.model.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by Sergio Carrillo Diestra on 11/03/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
class NetworkUtils {

    suspend inline fun <T> callFromRemote(crossinline retrofitCall: suspend () -> Response<T>): Either<Failure, T> {
        return try {
            withContext(Dispatchers.IO) {
                val response = retrofitCall.invoke()

                when (CommonHttpResponseCodes.fromInt(response.code())) {
                    CommonHttpResponseCodes.OK -> Either.Success(response.body()!!)
                    CommonHttpResponseCodes.MALFORMED_REQUEST -> Either.Error(Failure.MALFORMED_REQUEST_ERROR)
                    CommonHttpResponseCodes.NOT_FOUND -> Either.Error(Failure.NOT_FOUND_ERROR)
                    CommonHttpResponseCodes.UNAUTHORIZED -> Either.Error(Failure.UNAUTHORIZED_ERROR)
                    CommonHttpResponseCodes.INTERNAL_SERVER_ERROR -> Either.Error(Failure.INTERNAL_SERVER_ERROR)
                    CommonHttpResponseCodes.SERVICE_UNAVAILABLE -> Either.Error(Failure.UNKNOWN_ERROR)
                    null -> Either.Error(Failure.UNKNOWN_ERROR)
                }

            }
        } catch (e: Exception) {
            return Either.Error(Failure.UNKNOWN_ERROR)
        }
    }

}