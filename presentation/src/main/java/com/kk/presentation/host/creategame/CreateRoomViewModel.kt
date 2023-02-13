package com.kk.presentation.host.creategame

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kk.data.ISocketService
import com.kk.data.model.CreateGameRequest
import com.kk.data.model.HostUser
import com.kk.data.model.Rules
import com.kk.presentation.baseMVI.BaseViewModel
import kotlinx.coroutines.launch

class CreateRoomViewModel(private val socketServiceHost: ISocketService) : BaseViewModel<CreateRoomContract.Event, CreateRoomContract.State, CreateRoomContract.Effect>()
{



    override fun createInitialState(): CreateRoomContract.State {
        return CreateRoomContract.State(
            CreateRoomContract.CreateRoomState.Idle
        )
    }


    override fun handleEvent(event: CreateRoomContract.Event) {

         when (event) {
             CreateRoomContract.Event.OnChangePlayers -> {}
             CreateRoomContract.Event.OnChangePoints -> {}
             CreateRoomContract.Event.OnChangeTime -> {}
             CreateRoomContract.Event.OnCreateRoom -> {

                 viewModelScope.launch {

                 }

             }
         }
     }

    fun createRoom(){
        viewModelScope.launch {
            socketServiceHost.requestSocket(Gson().toJson(CreateGameRequest(HostUser(), rules = Rules(1,1,50))))
        }

    }


}