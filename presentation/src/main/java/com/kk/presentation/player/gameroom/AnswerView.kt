package com.kk.presentation.player.gameroom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.*
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnswerView(
    timeStamp: Long,
    navigateToHome: () -> Unit,
    navigateToWaitingPlayers: () -> Unit,
    viewModel: AnswerViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    KKBox(onClickConfirm = {viewModel.handleEvent(AnswerContract.Event.CloseSession)}) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp)
        ) {
            KkTitle(
                label = stringResource(R.string.ua_title,uiState.roundNumber),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 40.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))
            KkOrangeTitle(
                label = stringResource(R.string.ua_time, uiState.timer),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(15.dp))

            AnimatedVisibility(visible = !uiState.showTexFieldAndButton, modifier = Modifier
                .weight(1f)
                .fillMaxWidth()) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    KkTitle(label = stringResource(id = R.string.ua_waiting))
                }
            }


            AnimatedVisibility(visible = uiState.showTexFieldAndButton, modifier = Modifier
                .weight(1f)
                .fillMaxWidth()) {
                Column() {
                    KkBody(label = stringResource(R.string.ua_user_answer))
                    Spacer(modifier = Modifier.size(10.dp))
                    KkTextField(
                        value = uiState.answer,
                        onValueChange = {viewModel.handleEvent(AnswerContract.Event.OnChangeAnswer(it)) },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(25.dp))
                    KkButton(
                        onClick = { viewModel.setEvent(AnswerContract.Event.SendAnswer(timeStamp = timeStamp)) },
                        label = stringResource(R.string.ua_send_answer),
                        modifier = Modifier
                            .padding(bottom = 50.dp)
                            .fillMaxWidth()
                    )
                }

            }

        }
    }

    LaunchedEffect(key1 = Unit){
        viewModel.effect.collectLatest { effect ->
            when(effect){
                AnswerContract.Effect.NavigateToResults -> navigateToWaitingPlayers()
                AnswerContract.Effect.NavigateToHome -> navigateToHome()
            }
        }
    }

}