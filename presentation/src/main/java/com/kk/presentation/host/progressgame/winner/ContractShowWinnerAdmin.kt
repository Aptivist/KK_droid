package com.kk.presentation.host.progressgame.winner

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ContractShowWinnerAdmin {
    sealed class Event : UiEvent {
        object ShowWinnerName : Event()
        object NextGame : Event()
    }

    data class State(
        val error: String? = null,
        val round: Int = 1,
        val anyWinner: Boolean = false,
        val noWinner: Boolean = false,
        val gameWinner: Boolean = false,
        val winnerName: String = ""
    ) : UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}
