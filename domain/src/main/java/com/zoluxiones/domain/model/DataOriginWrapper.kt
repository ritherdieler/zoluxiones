package com.zoluxiones.domain.model

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
data class DataOriginWrapper<T>(
    val comesFromRemote: Boolean,
    val data: T,
    val totaPages: Int
)