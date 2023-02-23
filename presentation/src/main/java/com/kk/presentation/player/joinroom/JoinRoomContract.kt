package com.kk.presentation.player.joinroom

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class JoinRoomContract {
    sealed class Event : UiEvent {
        object OnJoinRoom : Event()
        data class OnChangeName(val name: String): Event()
        data class OnChangeCode(val code: String): Event()
        data class OnClickShowQR(val show : Boolean): Event()
        object OnScanQRCodeButtonClicked : Event()
        object OnJoinButtonClicked : Event()
        object CloseSession: Event()
    }

    data class State(
        val name: String = "",
        val code: String = "",
        val show: Boolean = false,
        val error: String? = null,
        val reJoin: Boolean = false,
        val isButtonEnabled : Boolean = false
    ) : UiState

    sealed class JoinRoomState {
        object Idle : JoinRoomState()
        object ScanningQRCodeState : JoinRoomState()
        object JoiningRoomState : JoinRoomState()
    }

    sealed class Effect : UiEffect {
        object Navigate : Effect()
        object NavigateToHome: Effect()
    }
}