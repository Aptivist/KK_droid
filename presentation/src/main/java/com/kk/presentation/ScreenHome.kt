package com.kk.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.R
import com.kk.designsystem.components.KkButton

@Composable
fun ScreenHome() {
    val image = if (isSystemInDarkTheme()) painterResource(id = R.drawable.logo_dark)
    else painterResource(
        id = R.drawable.logo_light
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Image(
            painter = image,
            contentDescription = "logo",
            alignment = Alignment.Center,
            modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 100.dp)
        )
        KkButton(
            onClick = { /*TODO*/ },
            label = "Crear partida",
            modifier = Modifier
                .padding(top = 170.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        KkButton(
            onClick = { /*TODO*/ },
            label = "Unirse a partida",
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}