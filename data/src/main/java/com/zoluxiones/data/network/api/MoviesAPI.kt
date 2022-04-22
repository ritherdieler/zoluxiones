package com.zoluxiones.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/


interface MoviesAPI {
    @GET("3/movie/upcoming")
    fun getMoviesByPage(@Query("page") page: Int, @Query("api_key") key: String)

}