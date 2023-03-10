package com.kk.presentation.player.gameroom.userquestionbutton

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class UserQuestionButtonContract {
    // Events that user performed
    sealed class Event : UiEvent {
        object OnMainButtonClicked : Event()
        object OnSkipButtonClicked : Event()
        object CloseSession : Event()
    }

    // View State
    data class State(
        val round: Int = 0,
        val timeStamp: Long = 0,
        val timer: Int = 0,
        val error: String? = null,
        val zIndex: Float = 0f,
        val showDialog: Boolean = false,
        val skipped: Boolean = false,
        val message: String? = null,
        val roundStarted: Boolean = false
    ) : UiState

    // Side Effect
    sealed class Effect : UiEffect {
        object NavigateToSendPlayerAnswer : Effect()
        object NavigateToResults : Effect()
        object NavigateToHome : Effect()
    }
}