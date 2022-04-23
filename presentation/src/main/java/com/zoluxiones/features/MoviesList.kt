package com.zoluxiones.features

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zoluxiones.domain.entity.Movie

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/

@Composable
fun MovieListUI(movies: List<Movie>, viewModel: MainViewModel = viewModel()) {

    val viewState = viewModel.viewState

    Column {
        ContentComposable(
            viewState.value,
            onLoadMore = { paginateMovies(viewModel) },
            onItemClicked = {})
    }

}

fun paginateMovies(viewModel: MainViewModel) {
    viewModel.getNextMoviesPage()
}

@Composable
fun ContentComposable(
    state: ViewState?,
    onLoadMore: () -> Unit,
    onItemClicked: () -> Unit,
) {
    when (state) {
        is ViewState.EmptyScreen -> {
            Text("Empty Screen")
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
    onItemClicked: () -> Unit
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
        LazyColumn(state = listState,
            modifier = Modifier.weight(1f).padding(top = 16.dp)) {
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
fun ItemComposable(movie: Movie, onItemClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp)
    ) {
        Text(text = movie.title, fontSize = 20.sp)
        Divider(modifier = Modifier.padding(vertical = 15.dp))
    }
}
