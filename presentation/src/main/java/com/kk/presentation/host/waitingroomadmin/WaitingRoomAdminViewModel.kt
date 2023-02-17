package com.kk.presentation.host.waitingroomadmin

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.WaitingRoomAdminRepository
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WaitingRoomAdminViewModel (private val waitingRoomAdminRepository: WaitingRoomAdminRepository,
                                 private val stringProvider: StringProvider
) : BaseViewModel<
        WaitingRoomAdminContract.Event,
        WaitingRoomAdminContract.State,
        WaitingRoomAdminContract.Effect>() {

    private var job: Job? = null
    init {
        observeData()
    }
    override fun createInitialState(): WaitingRoomAdminContract.State {
        return WaitingRoomAdminContract.State()
    }

    override fun handleEvent(event: WaitingRoomAdminContract.Event) {
        when(event){
            WaitingRoomAdminContract.Event.OnStartGame -> {
                sendEvent()
                setEffect { WaitingRoomAdminContract.Effect.Navigate }
                job?.cancel()
            }
        }
    }

    private fun sendEvent(){
        viewModelScope.launch(Dispatchers.IO){
            waitingRoomAdminRepository.sendRequest(eventRequestDomain = EventRequestDomain("START_GAME"))
        }

    }

    private fun observeData(){
       job = viewModelScope.launch(Dispatchers.IO){
            waitingRoomAdminRepository.receivePlayers().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.wr_error_connection)) }
                    is BaseResult.Success ->{
                        setState { copy(players = result.data.data) }
                    }
                }
            }
        }
    }

}