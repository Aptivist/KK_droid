package com.kk.presentation.player.gameroom

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class AnswerContract {
    // Events that user performed
    sealed class Event : UiEvent {
        object CloseSession: Event()
        data class OnChangeAnswer(val answer: String): Event()
        data class SendAnswer(val timeStamp: Long) : Event()
    }

    // UI View States
    data class State(
        val showTexFieldAndButton: Boolean = true,
        val answer: String = "",
        val error: String? = null,
        val timer: String = ""
    ) : UiState

    // Side Effect
    sealed class Effect : UiEffect {
        object NavigateToResults : Effect()
        object NavigateToHome: Effect()
    }
}
