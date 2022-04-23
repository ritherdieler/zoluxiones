package com.zoluxiones.features.movieList

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.features.destinations.MovieDetailDestination

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/


@Destination
@Composable
fun MovieListUI(navigator: DestinationsNavigator, viewModel: MovieListViewModel = hiltViewModel()) {

    val viewState = viewModel.viewState

    viewModel.getFirstMoviesPage()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de peliculas") })
        })
    {
        ContentComposable(
            viewState.value,
            onLoadMore = { paginateMovies(viewModel) },
            onItemClicked = {
                navigateToMovieDetail(navigator, it)
            })
    }

}

fun navigateToMovieDetail(navigator: DestinationsNavigator, movie: Movie) {
    navigator.navigate(MovieDetailDestination(movie))
}

fun paginateMovies(viewModel: MovieListViewModel) {
    viewModel.getNextMoviesPage()
}

@Composable
fun ContentComposable(
    state: ViewState?,
    onLoadMore: () -> Unit,
    onItemClicked: (movie: Movie) -> Unit,
) {
    when (state) {
        is ViewState.EmptyScreen -> {
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Cargando Datos...",
                    textAlign = TextAlign.Center,
                )
            }

        }
        is ViewState.Loaded -> {
            LoadedComposable(state, onLoadMore, onItemClicked)
        }
        ViewState.Loading -> {
            Text("Initial Loading")
        }
    }
}

@Composable
private fun LoadedComposable(
    state: ViewState.Loaded,
    onLoadMore: () -> Unit,
    onItemClicked: (movie: Movie) -> Unit
) {
    val listState = rememberLazyListState()

    val isScrollToEnd = remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    if (isScrollToEnd.value && !state.loadingMore) {
        onLoadMore()
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(

            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
        ) {
            items(state.data.size) {
                ItemComposable(state.data[it], onItemClicked)
            }
        }
        if (state.loadingMore) {
            CircularProgressIndicator(color = Color.Red)
        }
    }

}

@Composable
fun ItemComposable(movie: Movie, onItemClicked: (movie: Movie) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp)
            .clickable { onItemClicked.invoke(movie) }

    ) {
        Text(text = movie.title, fontSize = 20.sp)
        Divider(modifier = Modifier.padding(vertical = 15.dp))
    }
}
