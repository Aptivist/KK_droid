package com.kk.presentation.player.joinroom

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState
import com.kk.presentation.host.creategame.CreateRoomContract

class JoinRoomContract {
    sealed class Event : UiEvent {
        object OnJoinRoom : Event()
        data class OnChangeName(val name: String): Event()
        data class OnChangeCode(val code: String): Event()
        object OnScanQRCodeButtonClicked : Event()
        object OnJoinButtonClicked : Event()
    }

    data class State(
        val name: String = "",
        val code: String = "",
        val error: String? = null,
    ) : UiState

    sealed class JoinRoomState {
        object Idle : JoinRoomState()
        object ScanningQRCodeState : JoinRoomState()
        object JoiningRoomState : JoinRoomState()
    }

    sealed class Effect : UiEffect { object Navigate : Effect() }
}