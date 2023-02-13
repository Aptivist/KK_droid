package com.kk.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.R
import com.kk.designsystem.components.KKAlertDialog
import com.kk.designsystem.components.KKBox
import com.kk.designsystem.components.KkButton
import com.kk.presentation.R.string.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(
    navigateToCreateRoom: () -> Unit,
    navigateToJoinRoom: () -> Unit,
    viewModel: HomeVieModel = koinViewModel()
) {
    val image = if (isSystemInDarkTheme()) painterResource(id = R.drawable.logo_dark)
    else painterResource(id = R.drawable.logo_light)

    val uiState by viewModel.uiState.collectAsState()

    KKBox(isLoading = uiState.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = "logo",
                modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 100.dp)
            )
            KkButton(
                onClick = { viewModel.setEvent(HomeContract.Event.OnConnectHost) },
                label = stringResource(hs_create_room),
                modifier = Modifier
                    .padding(top = 170.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            )

            KkButton(
                onClick = { viewModel.setEvent(HomeContract.Event.OnConnectPlayer) },
                label = stringResource(hs_join_room),
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 50.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }
    }


    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when(effect){
                HomeContract.Effect.NavigateHost -> navigateToCreateRoom()
                HomeContract.Effect.NavigatePlayer -> navigateToJoinRoom()
            }
        }
    }

    KKAlertDialog(
        visible = uiState.error != null,
        title = stringResource(id = hs_error_title),
        message = stringResource(id = hs_error_message) ,
        onConfirm = { viewModel.setEvent(HomeContract.Event.OnRestartSate) },
        textConfirmButton = stringResource(id = hs_text_confirm_button)
    )
}