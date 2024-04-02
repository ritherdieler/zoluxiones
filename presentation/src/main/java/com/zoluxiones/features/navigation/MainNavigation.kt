package com.zoluxiones.features.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zoluxiones.features.login.LoginUI
import com.zoluxiones.features.movieDetail.MovieDetail
import com.zoluxiones.features.movieDetail.MovieHelper
import com.zoluxiones.features.movieList.MovieListUI
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@Composable
fun MainNavigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Routes.Main.Login.route) {

        composable(Routes.Main.Login.route) {
            LoginUI(navHostController = navHostController)
        }

        composable(Routes.Main.MovieList.route) {
            MovieListUI(navHostController = navHostController)
        }

        composable(
            route = Routes.Main.MovieDetail.route,
            arguments = listOf(
                navArgument(Routes.NavArgs.Movie.key) {
                    type = parcelableTypeOf<MovieHelper>()
                }
            )
        ) {
            it.arguments?.getParcelable<MovieHelper>(Routes.NavArgs.Movie.key)?.let { movie->
                MovieDetail(movie)
            }
        }
    }

}


@OptIn(InternalSerializationApi::class)
inline fun <reified T : Parcelable> parcelableTypeOf() =
    object : NavType<T>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(T::class.serializer(), Uri.decode(value))
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }

    }

