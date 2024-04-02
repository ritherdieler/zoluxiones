package com.zoluxiones.domain.model

/**
 * Created by Sergio Carrillo Diestra on 11/03/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
sealed class Either<out L, out R> {

    data class Error<out L>(val error: L) : Either<L, Nothing>()
    data class Success<out R>(val data: R) : Either<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isError get() = this is Error<L>

    fun either(fnL: (L) -> Unit, fnR: (R) -> Unit): Any =
        when (this) {
            is Error -> fnL(error)
            is Success -> fnR(data)
        }

    fun getOrNull(): R? =
        when (this) {
            is Success -> data
            else -> null
        }

    fun errorOrNull(): L? =
        when (this) {
            is Error -> error
            else -> null
        }
}
