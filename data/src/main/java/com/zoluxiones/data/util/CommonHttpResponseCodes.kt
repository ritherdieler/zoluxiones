package com.zoluxiones.data.util

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
enum class CommonHttpResponseCodes(val code: Int) {
    OK(200),
    MALFORMED_REQUEST(400),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    companion object {
        fun fromInt(value: Int): CommonHttpResponseCodes? {
            return when (value) {
                in 200..299 -> OK
                else -> values().firstOrNull { it.code == value }
            }
        }
    }
}