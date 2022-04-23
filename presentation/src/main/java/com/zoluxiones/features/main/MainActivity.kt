package com.zoluxiones.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ramcosta.composedestinations.DestinationsNavHost
import com.zoluxiones.features.NavGraphs
import com.zoluxiones.features.movieList.MovieListViewModel
import com.zoluxiones.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

}