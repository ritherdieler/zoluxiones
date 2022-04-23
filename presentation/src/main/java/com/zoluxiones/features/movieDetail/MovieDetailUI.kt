package com.zoluxiones.features.movieDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.theme.MyTheme

@Destination()
@Composable
fun MovieDetail(movie: Movie) {

    Scaffold {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(vertical = 36.dp)
                    .width(250.dp)
                    .height(290.dp)
                    .background(Color.Gray)
            )

            ProvideTextStyle(value = MaterialTheme.typography.h5) {
                Text(
                    text = movie.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Row {
                Text(text = movie.vote_average.toString())
                Icon(imageVector = Icons.Filled.Star, contentDescription = "rate icon")
            }
            Text(text = movie.release_date)
            Divider(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))
            Text(
                text = movie.overview,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 36.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme() {
    }
}