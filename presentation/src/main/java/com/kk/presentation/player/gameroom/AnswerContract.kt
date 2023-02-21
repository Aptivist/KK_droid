package com.kk.presentation.player.gameroom

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class AnswerContract {
    // Events that user performed
    sealed class Event : UiEvent {
        object SendAnswer : Event()
    }

    // UI View States
    data class State(
        val error: String? = null,
        val timer: String = ""
    ) : UiState

    // Side Effect
    sealed class Effect : UiEffect {
        object NavigateHost : Effect()
        object NavigatePlayer : Effect()
    }
}
