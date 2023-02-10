package com.kk.presentation.host.waitingroomadmin

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState
import com.kk.presentation.host.creategame.CreateRoomContract

class WaitingRoomAdminContract {
    sealed class Event : UiEvent{
        object onWaitingRoomAdmin : Event()
    }

    data class State(val waitingRoomAdminState : WaitingRoomAdmin) : UiState

    sealed class WaitingRoomAdmin {
        object Idle : WaitingRoomAdmin()
        data class Error(val message: String) : WaitingRoomAdmin()
        data class Success(val value: Int) : WaitingRoomAdmin()
    }

    sealed class Effect : UiEffect { object Nav : Effect() }
}