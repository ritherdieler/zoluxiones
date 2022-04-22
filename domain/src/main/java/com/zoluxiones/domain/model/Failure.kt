package com.zoluxiones.domain.model

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
sealed class Failure {
    object MALFORMED_REQUEST_ERROR : Failure()
    object NOT_FOUND_ERROR : Failure()
    object UNAUTHORIZED_ERROR : Failure()
    object INTERNAL_SERVER_ERROR : Failure()
    object SERVICE_UNAVAILABLE_ERROR : Failure()
    object UNKNOWN_ERROR : Failure()
    object LOCAL_DB_ERROR:Failure()
    object MAPPER_ERROR:Failure()
}