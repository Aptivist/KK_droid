package com.kk.presentation.host.waitingroomadmin

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.WaitingRoomAdminRepository
import com.kk.domain.models.QRGenerator
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.local.domain.PreferencesRepository
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WaitingRoomAdminViewModel (private val waitingRoomAdminRepository: WaitingRoomAdminRepository,
                                 private val stringProvider: StringProvider,
                                 private val dataStoreRepository : PreferencesRepository,
                                 private val qrGenerator: QRGenerator
) : BaseViewModel<
        WaitingRoomAdminContract.Event,
        WaitingRoomAdminContract.State,
        WaitingRoomAdminContract.Effect>() {

    private var job: Job? = null
    init {
        getQRCode()
        observeData()
    }
    override fun createInitialState(): WaitingRoomAdminContract.State {
        return WaitingRoomAdminContract.State()
    }

    override fun handleEvent(event: WaitingRoomAdminContract.Event) {
        when(event){
            WaitingRoomAdminContract.Event.OnStartGame -> {
                sendEvent()

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
                        if (result.data.status =="INITIALIZED"){
                            setEffect { WaitingRoomAdminContract.Effect.Navigate }
                            job?.cancel()
                        }
                    }
                }
            }
        }
    }

    private fun getQRCode(){
        viewModelScope.launch (Dispatchers.IO){
            val gameCode = dataStoreRepository.getGameCode()
            val qrCode = qrGenerator.encodeString(gameCode)
            setState { copy(codeQR = qrCode) }
        }
    }

}