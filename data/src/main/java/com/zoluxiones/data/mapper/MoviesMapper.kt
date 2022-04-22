package com.zoluxiones.data.mapper

import com.zoluxiones.data.network.response.MoviesResponse
import com.zoluxiones.domain.entity.Movie

class MoviesMapper {
    fun ToDomainList(moviesResponse: List<com.zoluxiones.data.database.model.Movie>): List<Movie> =
        moviesResponse.map { movie ->
            Movie(
                id = movie.id,
                poster_path = movie.poster_path,
                title = movie.title,
                vote_average = movie.vote_average,
                release_date = movie.release_date,
                overview = movie.overview
            )
        }


    fun ToDatabaseList(moviesResponse: MoviesResponse): List<com.zoluxiones.data.database.model.Movie> =
        moviesResponse.results.map { movie ->
            com.zoluxiones.data.database.model.Movie(
                id = movie.id,
                poster_path = movie.poster_path,
                title = movie.title,
                vote_average = movie.vote_average,
                release_date = movie.release_date,
                overview = movie.overview
            )
        }

}