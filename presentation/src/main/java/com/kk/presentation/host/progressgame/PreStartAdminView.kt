package com.kk.presentation.host.progressgame

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.*
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun PreStartAdminView(
    navigateToWaitingView : () -> Unit,
    viewModel: ProgressGameViewModel = koinViewModel()
){
    //viewmodel round: String
    val uiState by viewModel.uiState.collectAsState()
    KKBox(isLoading = uiState.isLoading) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                KkTitle(label = viewModel.round)
            }
            Box(modifier = Modifier.padding(25.dp)) {
                KkBody(label = stringResource(id = R.string.question_to_ask))
            }
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                KkOrangeTitle(
                    label = stringResource(id = R.string.start_round),
                    onClick = { viewModel.setEvent(ContractProgressGame.Event.StartRound) }
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit){
        viewModel.effect.collectLatest { effect ->
            when(effect){
                ContractProgressGame.Effect.NavigateWaitingAnswerHost -> navigateToWaitingView()
            }
        }
    }
}