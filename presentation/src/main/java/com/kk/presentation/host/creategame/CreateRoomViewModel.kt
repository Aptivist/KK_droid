package com.kk.presentation.host.creategame

import com.kk.presentation.baseMVI.BaseViewModel

class CreateRoomViewModel() :
    BaseViewModel<CreateRoomContract.Event, CreateRoomContract.State, CreateRoomContract.Effect>() {
    override fun createInitialState(): CreateRoomContract.State {
        return CreateRoomContract.State(
            CreateRoomContract.CreateRoomState.Idle
        )
    }

    override fun handleEvent(event: CreateRoomContract.Event) {

        when(event){
            CreateRoomContract.Event.OnChangePlayers -> {}
            CreateRoomContract.Event.OnChangePoints -> {}
            CreateRoomContract.Event.OnChangeTime -> {}
            CreateRoomContract.Event.OnCreateRoom -> {}
        }
    }
}