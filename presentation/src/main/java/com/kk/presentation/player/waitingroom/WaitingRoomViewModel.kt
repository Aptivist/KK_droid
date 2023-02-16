package com.kk.presentation.player.waitingroom

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.PlayerWaitingRoomRepository
import com.kk.domain.models.BaseResult
import com.kk.presentation.baseMVI.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WaitingRoomViewModel(private val repository: PlayerWaitingRoomRepository): BaseViewModel<WaitingRoomContract.Event, WaitingRoomContract.State, WaitingRoomContract.Effect>() {
    init{
        observe()
    }

    override fun createInitialState(): WaitingRoomContract.State {
        return WaitingRoomContract.State()
    }

    override fun handleEvent(event: WaitingRoomContract.Event) {

    }

    private fun observe(){
        viewModelScope.launch(Dispatchers.IO){
            repository.showPlayers().collect{ result ->
                when(result){
                    is BaseResult.Error -> setState {
                        copy(error = result.toString())
                    }
                    is BaseResult.Success -> {
                        setState {
                            copy(playerList = result.data.data)
                        }

                        if(result.data.status == "INITIALIZED"){
                            setEffect {
                                WaitingRoomContract.Effect.Navigate
                            }
                        }
                    }
                }
            }
        }
    }
}

