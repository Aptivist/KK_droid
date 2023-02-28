package com.kk.presentation.player.resultroom

import com.kk.domain.models.PlayerUserDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ResultRoomContract {

    sealed class Event : UiEvent {
        object OnGoHomeClicked : Event()
        object CloseSession: Event()
    }

    //TODO("USE SEALED CLASS or ENUM instead of STRING (status)")
    data class State(
        val status: String = "WAITING",
        val error: String? = null,
        val title: String = "",
        val winnerName: String = "",
        val players: List<PlayerUserDomain> = emptyList(),
        ): UiState

    sealed class Effect : UiEffect {
        object NavigateToNextRound : Effect()
        object NavigateToHome: Effect()
    }
}