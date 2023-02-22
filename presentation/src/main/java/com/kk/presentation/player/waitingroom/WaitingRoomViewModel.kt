package com.kk.presentation.player.waitingroom

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.PlayerWaitingRoomRepository
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.presentation.baseMVI.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WaitingRoomViewModel(private val repository: PlayerWaitingRoomRepository): BaseViewModel<WaitingRoomContract.Event,WaitingRoomContract.State, WaitingRoomContract.Effect>() {
    private var job: Job? = null

    init{
        observe()
        showPlayers()
    }

    override fun createInitialState(): WaitingRoomContract.State {
        return WaitingRoomContract.State()
    }

    override fun handleEvent(event: WaitingRoomContract.Event) {
        when(event){
            WaitingRoomContract.Event.CloseSession -> {
                closeSession()
                setEffect { WaitingRoomContract.Effect.NavigateToHome }
            }
        }
    }

    private fun showPlayers(){
        viewModelScope.launch {
            repository.showPlayers(EventRequestDomain("SHOW_PLAYERS"))
        }
    }

    private fun closeSession(){
        viewModelScope.launch(Dispatchers.IO){
            repository.closeSession()
        }
    }

    private fun observe(){
        job = viewModelScope.launch(Dispatchers.IO){
            repository.retrievePlayers().collect{ result ->
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
                            job?.cancel()
                        }
                    }
                }
            }
        }
    }
}

