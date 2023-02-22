package com.kk.presentation.host.creategame

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.*
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateRoomView(
    navigateToHome : () -> Unit,
    navigateToWaitingRoom : (code: String) -> Unit,
    viewModel: CreateRoomViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    KKBox (onClickConfirm = {viewModel.handleEvent(CreateRoomContract.Event.CloseSession)}){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            KkTitle(
                label = stringResource(R.string.cr_create_room),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 40.dp)
            )
            KkBody(label = stringResource(R.string.cr_participants))
            Spacer(modifier = Modifier.size(10.dp))
            KkNumberField(
                value = uiState.players.toString(),
                onValueChange = {
                    viewModel.handleEvent(CreateRoomContract.Event.OnChangePlayers( it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.size(20.dp))
            KkBody(label = stringResource(R.string.cr_points))
            Spacer(modifier = Modifier.size(10.dp))
            KkNumberField(
                value = uiState.points.toString(),
                onValueChange = {
                    viewModel.handleEvent(CreateRoomContract.Event.OnChangePoints(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.size(20.dp))
            KkBody(label = stringResource(R.string.cr_time))
            Spacer(modifier = Modifier.size(10.dp))
            KkNumberField(
                value = uiState.time.toString(),
                onValueChange = {
                    viewModel.handleEvent(CreateRoomContract.Event.OnChangeTime(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(0.dp)
            , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
            )

            // TODO (This line is just for example Delete comment)
            //KkBody(label = uiState.toString())

            Spacer(modifier = Modifier.weight(1f))
            KkButton(
                onClick = {
                    viewModel.handleEvent(CreateRoomContract.Event.OnCreateRoom) },
                label = stringResource(R.string.cr_create),
                modifier = Modifier
                    .padding(vertical = 50.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }
    }


    LaunchedEffect(Unit){
        viewModel.effect.collectLatest {
            when(it){
                CreateRoomContract.Effect.Navigate -> navigateToWaitingRoom(uiState.code)
                CreateRoomContract.Effect.NavigateToHome -> navigateToHome()
            }
        }
    }
}