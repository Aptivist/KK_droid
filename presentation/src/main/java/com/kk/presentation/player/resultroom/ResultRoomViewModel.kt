package com.kk.presentation.player.resultroom

import com.kk.presentation.baseMVI.BaseViewModel

class ResultRoomViewModel :
    BaseViewModel<ResultRoomContract.Event, ResultRoomContract.State, ResultRoomContract.Effect>() {
    override fun createInitialState(): ResultRoomContract.State {
        return ResultRoomContract.State(
            ResultRoomContract.ResultRoomState.Idle
        )
    }

    override fun handleEvent(event: ResultRoomContract.Event) {
        when (event) {
            ResultRoomContract.Event.OnGoHomeClicked -> {
                ResultRoomContract.ResultRoomState.NavigateToHome
            }
        }
    }
}