package com.kk.presentation.player.gameroom

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class GamingRoomContract {

    sealed class Event : UiEvent {
        object OnQuestionButtonClicked : Event()
        object OnQuestionSkipButtonClicked : Event()
        data class SendAnswerButtonClicked(val answer: String) : Event()
    }

    data class State(val gamingRoomState: GamingRoomState) : UiState

    sealed class GamingRoomState {
        object Idle : GamingRoomState()
        data class RoundAndTimeState(val roundNumber: Int, var time: String) : GamingRoomState()
    }

    sealed class Effect : UiEffect

}