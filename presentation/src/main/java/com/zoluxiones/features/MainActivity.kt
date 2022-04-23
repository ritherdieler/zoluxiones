package com.zoluxiones.features

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoluxiones.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFirstMoviesPage()
        observer()

        setContent {
            MyTheme {
                MainComposeUI()
            }
        }
    }

    private fun observer() {
        viewModel.errorState.observe(this)
        {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Composable
    fun MainComposeUI() {

        val movies = viewModel.moviesListState
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Lista de peliculas") })
            })
        {

                MovieListUI(movies.value)

        }
    }
}