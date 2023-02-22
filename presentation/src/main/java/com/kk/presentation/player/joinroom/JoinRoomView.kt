package com.kk.presentation.player.joinroom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iteneum.core.ui.components.camera.GKCameraScannerView
import com.kk.designsystem.components.*
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun JoinRoomView(
    navigateToHome: () -> Unit,
    navigateToWaitingRoom: () -> Unit,
    viewModel: JoinRoomViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    KKBox(onClickConfirm = {viewModel.handleEvent(JoinRoomContract.Event.CloseSession)} ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            KkTitle(
                label = stringResource(R.string.jg_join_room_title),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 40.dp)
            )
            KkBody(label = stringResource(R.string.jr_name))
            Spacer(modifier = Modifier.size(10.dp))
            KkTextField(
                value = uiState.name,
                onValueChange = {
                    viewModel.handleEvent(JoinRoomContract.Event.OnChangeName(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(20.dp))
            KkBody(label = stringResource(R.string.jr_code))
            Spacer(modifier = Modifier.size(10.dp))
            KkTextField(
                value = uiState.code,
                onValueChange = {
                    viewModel.handleEvent(JoinRoomContract.Event.OnChangeCode(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            if (!viewModel.uiState.value.show ?: false) {
                KkButton(
                    onClick = {
                        viewModel.handleEvent(JoinRoomContract.Event.OnClickShowQR(true))
                    },
                    label = stringResource(R.string.jr_scan_code),
                    modifier = Modifier
                        .padding(vertical = 40.dp)
                        .fillMaxWidth()
                )
            } else {

                GKCameraScannerView(modifier =
                Modifier
                    .padding(vertical = 40.dp)
                    .fillMaxWidth()
                    .height(300.dp),
                    onResult = {
                        viewModel.handleEvent(JoinRoomContract.Event.OnClickShowQR(false))
                        viewModel.handleEvent(JoinRoomContract.Event.OnChangeCode(it))
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            KkButton(
                onClick = { viewModel.handleEvent(JoinRoomContract.Event.OnJoinButtonClicked) },
                label = stringResource(R.string.jr_join_room),
                modifier = Modifier
                    .padding(vertical = 50.dp)
                    .fillMaxWidth()
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                JoinRoomContract.Effect.Navigate -> navigateToWaitingRoom()
                JoinRoomContract.Effect.NavigateToHome -> navigateToHome()
            }
        }
    }
}