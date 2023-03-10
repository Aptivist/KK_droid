package com.kk.presentation.host.creategame

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState


class CreateRoomContract {
    // Events that user performed
    sealed class Event : UiEvent {
        object OnCreateRoom : Event()
        data class OnChangePlayers(val players: String): Event()
        data class OnChangePoints(val points: String): Event()
        data class OnChangeTime(val time: String ): Event()
        object ClearError : Event()
        object CloseSession: Event()
    }

    // View State
    data class State(
        var code: String = "",
        val players: Int = 0,
        val time: Int = 0,
        val points : Int = 0,
        val data: String = "",
        val error: String? = null,
        val isLoading: Boolean = false
    ) : UiState

    // Side Effect
    sealed class Effect : UiEffect {
        object Navigate : Effect()
        object NavigateToHome: Effect()
    }
}