package com.kk.presentation.player.resultroom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.kk.designsystem.components.KKBox
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ResultView(
    navigateToHome: () -> Unit,
    navigateToNextRound: () -> Unit,
    viewModel: ResultRoomViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    KKBox(onClickConfirm = {
        viewModel.handleEvent(ResultRoomContract.Event.CloseSession)
    }) {
        AnimatedVisibility(visible = uiState.status == "WAITING") {
            WaitingResultView(title = uiState.title)
        }
        AnimatedVisibility(visible = uiState.status == "WINNER_ROUND") {
            WinnerRoundView(title = uiState.title)
        }
        AnimatedVisibility(visible = uiState.status == "GAME_FINISHED_WON") {
            WinnerGameView(title = uiState.title, players = uiState.players, onClickHome = {viewModel.handleEvent(ResultRoomContract.Event.OnGoHomeClicked)})
        }
        AnimatedVisibility(visible = uiState.status == "NO_WINNER_ROUND") {
            NoPointsRoundView(title = uiState.title)
        }
        AnimatedVisibility(visible = uiState.status == "ROUND_FINISHED") {
            LoserRoundView(title = uiState.title,uiState.winnerName)
        }
        AnimatedVisibility(visible = uiState.status == "GAME_FINISHED_LOSE") {
            LoserGameView(title = uiState.title, players = uiState.players, onClickHome = {viewModel.handleEvent(ResultRoomContract.Event.OnGoHomeClicked)})
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                ResultRoomContract.Effect.NavigateToNextRound -> {
                    navigateToNextRound()
                }
                ResultRoomContract.Effect.NavigateToHome -> {
                    navigateToHome()
                }
            }
        }

    }
}






