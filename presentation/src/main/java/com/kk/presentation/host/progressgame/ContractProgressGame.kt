package com.kk.presentation.host.progressgame

import com.kk.domain.models.PlayersAnswerDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ContractProgressGame {
    sealed class Event : UiEvent {
        object StartRound : Event()

    }

    data class State(
        val error: String? = null,
        val timeLeft: String = ""
        ): UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}