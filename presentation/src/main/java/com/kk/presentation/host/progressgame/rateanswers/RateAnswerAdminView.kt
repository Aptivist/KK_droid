package com.kk.presentation.host.progressgame.rateanswers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.*
import com.kk.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RateAnswerAdminView(/*
    navigateToEndRoundHost: () -> Unit,
    viewModel: ProgressGameViewModel = koinViewModel()*/
){/*
    //VIEWMODEL round: String, playerAnswer: String, showSkip:Boolean
    val playerAnswer = "bla bla bla"
    val showSkip = false
    val uiState by viewModel.uiState.collectAsState()

    KKBox{
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                KkTitle(label = "")
            }
            Box(modifier = Modifier.padding(25.dp, 0.dp)) {
                KkBody(label = stringResource(id = R.string.player_answer))
            }
            Box(modifier = Modifier.padding(25.dp)) {
                KkBody(label = playerAnswer)
            }
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 30.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Bottom) {
                AnimatedVisibility(visible = showSkip) {
                    KkButton(onClick = { /*viewModel.setEvent(ContractProgressGame.Event.SkipAnswer)*/ },
                        label = stringResource(id = R.string.skip))
                }
                AnimatedVisibility(visible = !showSkip) {
                    KkIncorrectButton(
                        onClick = { /*viewModel.setEvent(ContractProgressGame.Event.IncorrectAnswer)*/ },
                        label = stringResource(id = R.string.incorrect_answer_button)
                    )

                }
                AnimatedVisibility(visible = !showSkip) {
                    KkCorrectButton(
                        onClick = { /*viewModel.setEvent(ContractProgressGame.Event.CorrectAnswer)*/ },
                        label = stringResource(id = R.string.correct_answer_button)
                    )
                }
            }
        }
    }*/
}