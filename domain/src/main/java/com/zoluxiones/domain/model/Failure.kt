package com.zoluxiones.domain.model

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
sealed class Failure(val message:String) {
    object MALFORMED_REQUEST_ERROR : Failure("Los datos enviado no son validos")
    object NOT_FOUND_ERROR : Failure("El recurso solicitado no existe")
    object UNAUTHORIZED_ERROR : Failure("No està autorizado para realizar esta operaciòn")
    object INTERNAL_SERVER_ERROR : Failure("Ocurrio un error en el servidor, por favor contacte con soporte tècnico")
    object SERVICE_UNAVAILABLE_ERROR : Failure("El servicio no esta disponible")
    object UNKNOWN_ERROR : Failure("Ocurrio un erro desconocido, por favor contacte con el soporte tecnico")
    object LOCAL_DB_ERROR:Failure("Ocurrio un error interno, por favor contacte con el soporte tecnico")
    object MAPPER_ERROR:Failure("Ocurrio un error interno, por favor contacte con el soporte tecnico")
}