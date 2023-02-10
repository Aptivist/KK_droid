package com.kk.presentation.host.waitingroomadmin

import com.kk.presentation.baseMVI.BaseViewModel

class WaitingRoomAdminViewModel : BaseViewModel <
            WaitingRoomAdminContract.Event,
            WaitingRoomAdminContract.State,
            WaitingRoomAdminContract.Effect > ()
{
    override fun createInitialState(): WaitingRoomAdminContract.State {
        return WaitingRoomAdminContract.State(
            WaitingRoomAdminContract.WaitingRoomAdmin.Idle
        )
    }

    override fun handleEvent(event: WaitingRoomAdminContract.Event) {
        when(event){
            is WaitingRoomAdminContract.Event.onWaitingRoomAdmin -> {}
        }
    }

}