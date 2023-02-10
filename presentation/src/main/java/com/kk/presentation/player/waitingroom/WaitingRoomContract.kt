package com.kk.presentation.player.waitingroom

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class WaitingRoomContract {
    sealed class Event : UiEvent

    data class State(val waitingRoomState: WaitingRoomState) : UiState

    sealed class WaitingRoomState {
        object Idle : WaitingRoomState()
        data class WaitingPlayersState(val playerList: List<String>) : WaitingRoomState()
        object ReadyState : WaitingRoomState()
    }

    sealed class Effect : UiEffect

}