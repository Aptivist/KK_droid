package com.kk.presentation.host.progressgame

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ContractProgressGame {
    sealed class Event : UiEvent {
        object StartRound : Event()
        object RateAnswers: Event()
    }

    data class State(
        val error: String? = null,
        val timeLeft: String = "",
        val round: Int = 1,
        val preStartState: Boolean = false,
        val awaitingAnswersState: Boolean = false
        ): UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}