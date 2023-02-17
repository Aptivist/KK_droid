package com.kk.presentation.host.progressgame

import androidx.compose.animation.AnimatedVisibility
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
    navigateToRateAnswerAdminView : () -> Unit,
    viewModel: ProgressGameViewModel = koinViewModel()
){

    val uiState by viewModel.uiState.collectAsState()

    KKBox {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                KkTitle(label = uiState.round.toString() +  "° Round")
            }
            AnimatedVisibility(visible = uiState.preStartState) {
                Box(modifier = Modifier.padding(25.dp)) {
                    KkBody(label = stringResource(id = R.string.question_to_ask))
                }
            }
            AnimatedVisibility(visible = uiState.timeLeft.isNotEmpty()) {
                Box(modifier = Modifier.padding(25.dp)) {
                    KkBody(label = stringResource(id = R.string.awaiting_body))
                }
            }

            AnimatedVisibility(visible = uiState.preStartState) {
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    KkOrangeTitle(
                        label = stringResource(id = R.string.start_round),
                        onClick = {
                            viewModel.setEvent(ContractProgressGame.Event.StartRound)
                        }
                    )
                }
            }
            AnimatedVisibility(visible = uiState.timeLeft.isNotEmpty()) {
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    KkOrangeTitle(label = uiState.timeLeft)
                    KkOrangeTitle(label = stringResource(id = R.string.timing))
                }
            }

        }
    }

    LaunchedEffect(key1 = Unit){
        viewModel.effect.collectLatest { effect ->
            when(effect){
                ContractProgressGame.Effect.Navigate -> navigateToRateAnswerAdminView()
            }
        }
    }
}