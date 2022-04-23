package com.zoluxiones.features.movieList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ramcosta.composedestinations.DestinationsNavHost
import com.zoluxiones.features.NavGraphs
import com.zoluxiones.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        observer()

        setContent {
            MyTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

//    private fun observer() {
//        viewModel.errorState.observe(this)
//        {
//            Toast.makeText(
//                this,
//                it,
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

//    @Composable
//    fun MainComposeUI() {
//
//        var navController = rememberNavController()
//
//        NavHost(navController = navController, startDestination = "movies") {
//            composable("login") { LoginUI(navController) }
//
//            composable(route = "movies") { MovieListUI(navController) }
//
//            composable(
//                route = "moviedetail",
//                arguments = listOf(
//                    navArgument("movie") {
//                        type =
//                            NavType.SerializableType(Movie::class.java)
//                    }
//                )
//            ) {
////                val movie = (navController.previousBackStackEntry?.arguments?.getSerializable("movie") as? Movie) ?: return@composable
//                MovieDetail()
//            }
//
//        }
//
//
//    }
}