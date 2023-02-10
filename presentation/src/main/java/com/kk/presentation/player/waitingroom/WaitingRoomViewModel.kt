package com.kk.presentation.player.waitingroom

import com.kk.presentation.baseMVI.BaseViewModel

class WaitingRoomViewModel :

    BaseViewModel<WaitingRoomContract.Event, WaitingRoomContract.State, WaitingRoomContract.Effect>() {

    override fun createInitialState(): WaitingRoomContract.State {
        return WaitingRoomContract.State(
            WaitingRoomContract.WaitingRoomState.Idle
        )
    }

    override fun handleEvent(event: WaitingRoomContract.Event) {

    }
}