package com.kk.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kk.designsystem.R
import com.kk.designsystem.components.KkButton
import com.kk.presentation.R.string.hs_create_room
import com.kk.presentation.R.string.hs_join_room

@Composable
fun ScreenHome(navController: NavController) {
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
            label = stringResource(hs_create_room),
            modifier = Modifier
                .padding(top = 170.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        KkButton(
            onClick = { /*TODO*/ },
            label = stringResource(hs_join_room),
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}