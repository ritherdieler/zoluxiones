package com.zoluxiones.features.navigation

import android.net.Uri
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.features.movieDetail.MovieHelper
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

const val LOGIN_ROUTE = "login"
const val MOVIE_LIST_ROUTE = "movie_list"
const val MOVIE_DETAIL_ROUTE = "movie_detail"

sealed class Routes {


    sealed class Main(val route: String) : Routes() {
        object Login : Main(LOGIN_ROUTE)


        object MovieList : Main(MOVIE_LIST_ROUTE) {
            fun createRoute() = MOVIE_LIST_ROUTE
        }

        object MovieDetail : Main("$MOVIE_DETAIL_ROUTE/{${NavArgs.Movie.key}}") {
            fun createRoute(movie: MovieHelper) =
                "$MOVIE_DETAIL_ROUTE/${
                    Uri.encode(
                        Json.encodeToJsonElement(movie).toString()
                    )
                }"
        }
    }

    enum class NavArgs(val key: String) {
        Movie("movie"),
    }

}

 fun Movie.toUi() = MovieHelper(
    id = id,
    poster_path = poster_path,
    title = title,
    vote_average = vote_average,
    release_date = release_date,
    overview = overview

)
