package com.kk.presentation.player

import com.kk.presentation.baseMVI.BaseViewModel

class JoinRoomViewModel :

    BaseViewModel<JoinRoomContract.Event, JoinRoomContract.State, JoinRoomContract.Effect>() {

    override fun createInitialState(): JoinRoomContract.State {
        return JoinRoomContract.State(
            JoinRoomContract.JoinRoomState.Idle
        )
    }

    override fun handleEvent(event: JoinRoomContract.Event) {
        when (event) {
            is JoinRoomContract.Event.OnJoinButtonClicked -> {
                joinRoom()
            }
            is JoinRoomContract.Event.OnScanQRCodeButtonClicked -> {
                scanQRCode()
            }
        }
    }

    private fun joinRoom() {
        TODO("Not yet implemented")
    }

    private fun scanQRCode() {
        TODO("Not yet implemented")
    }
}