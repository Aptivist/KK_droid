package com.kk.presentation.player

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class JoinRoomContract {
    sealed class Event : UiEvent {
        object OnScanQRCodeButtonClicked : Event()
        object OnJoinButtonClicked : Event()
    }

    data class State(val JoinRoomState: JoinRoomState) : UiState

    sealed class JoinRoomState {
        object Idle : JoinRoomState()
        object ScanningQRCodeState : JoinRoomState()
        object JoiningRoomState : JoinRoomState()
    }

    sealed class Effect : UiEffect
}