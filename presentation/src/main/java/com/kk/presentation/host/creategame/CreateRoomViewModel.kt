package com.kk.presentation.host.creategame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kk.data.ISocketService
import com.kk.data.model.BaseResponse
import com.kk.data.model.CreateGameRequest
import com.kk.data.model.HostUser
import com.kk.data.model.Rules
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CreateRoomViewModel(private val socketServiceHost: ISocketService) : ViewModel()
//BaseViewModel<CreateRoomContract.Event, CreateRoomContract.State, CreateRoomContract.Effect>()
{

    private val _uiState = MutableStateFlow(BaseResponse("",""))
    val uiState get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            //socketServiceHost.connectSocket()
        }


    }

    /*override fun createInitialState(): CreateRoomContract.State {
        return CreateRoomContract.State(
            CreateRoomContract.CreateRoomState.Idle
        )
    }*/


    /* override fun handleEvent(event: CreateRoomContract.Event) {

         when (event) {
             CreateRoomContract.Event.OnChangePlayers -> {}
             CreateRoomContract.Event.OnChangePoints -> {}
             CreateRoomContract.Event.OnChangeTime -> {}
             CreateRoomContract.Event.OnCreateRoom -> {

                 viewModelScope.launch {
                     socketServiceHost.receiveData()
                         .flowOn(Dispatchers.IO)
                         .collect {
                             Log.e("Collect",it.data.toString() +".")
                             setState { copy(createRoomState = CreateRoomContract.CreateRoomState.Success(it.data)) }
                         }
                 }

             }
         }
     }*/

    fun createRoom(){
        viewModelScope.launch {
            socketServiceHost.requestSocket(Gson().toJson(CreateGameRequest(HostUser(), rules = Rules(1,1,50))))
        }

    }

    fun rec(){
        viewModelScope.launch {
            socketServiceHost.receiveData().collect{base ->
                _uiState.update {
                    base
                }
            }
        }


    }



}