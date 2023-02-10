package com.kk.presentation.player.resultroom

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ResultRoomContract {

    sealed class Event : UiEvent {
        object OnGoHomeClicked : Event()
    }

    data class State(val resultRoomState: ResultRoomState) : UiState

    sealed class ResultRoomState {
        object Idle : ResultRoomState()
        object NavigateToHome : ResultRoomState()
        data class AnswerState(val roundNumber: Int, val text: String)
        data class GameWinnerState(val text: String)
        data class GameLostState(val winners: List<String>)
    }

    sealed class Effect : UiEffect

}