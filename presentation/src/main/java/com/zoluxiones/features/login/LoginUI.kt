package com.zoluxiones.features.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zoluxiones.features.destinations.MovieListUIDestination
import com.zoluxiones.theme.MyTheme

@Destination(start = true)
@Composable
fun LoginUI(navigator: DestinationsNavigator) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Iniciar Sesion") }) }

    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val usernameTxt = rememberSaveable { mutableStateOf("") }

            TextField(
                value = usernameTxt.value,
                onValueChange = { usernameTxt.value = it },
                label = { Text("Usuario") },
                placeholder = { Text("Digite su nombre de usuario") },
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            )


            val passwordTxt = rememberSaveable { mutableStateOf("") }
            TextField(
                value = passwordTxt.value,
                onValueChange = { passwordTxt.value = it },
                label = { Text("Contraseña") },
                placeholder = { Text("Digite su contraseña") },
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            val mContext = LocalContext.current
            Button(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
                onClick = {
                    navigateToMoviesList(
                        navigator,
                        usernameTxt.value.trim(),
                        passwordTxt.value.trim(),
                        mContext
                    )
                }) {
                Text("Entrar", fontSize = 16.sp)
            }
        }
    }
}

fun navigateToMoviesList(
    navigator: DestinationsNavigator,
    username: String,
    password: String,
    mContext: Context,
) {

    if (username == "Admin" && password == "Password*123")
        navigator.navigate(MovieListUIDestination)
    else
        Toast.makeText(mContext, "Usuario o contraseña no validos", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme() {
//        LoginUI()
    }
}