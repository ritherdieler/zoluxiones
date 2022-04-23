package com.zoluxiones.features.movieDetail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.theme.MyTheme

@Destination()
@Composable
fun MovieDetail(movie:Movie) {
    val mContext = LocalContext.current
    Toast.makeText(mContext, movie.title, Toast.LENGTH_SHORT).show()
    Scaffold {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme() {
    }
}