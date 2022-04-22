package com.zoluxiones.data.network.api

import com.zoluxiones.data.network.response.MoviesResponse
import retrofit2.Response
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
    suspend fun getMoviesByPage(
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Response<MoviesResponse>

}