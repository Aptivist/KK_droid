package com.kk.presentation.player.joinroom

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.kk.data.repository.JoinRoomRepository
import com.kk.domain.models.BaseResult
import com.kk.domain.models.JoinRoomDomain
import com.kk.local.domain.PreferencesRepository
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class JoinRoomViewModel(private val joinRoomRepository: JoinRoomRepository, private val stringProvider: StringProvider,private val dataStoreRepository: PreferencesRepository) : BaseViewModel<JoinRoomContract.Event, JoinRoomContract.State, JoinRoomContract.Effect>() {

    private var job: Job? = null

    init {
        observeData()
    }

    override fun createInitialState(): JoinRoomContract.State {
        return JoinRoomContract.State()
    }

    override fun handleEvent(event: JoinRoomContract.Event) {
        when (event) {
            is JoinRoomContract.Event.OnJoinButtonClicked -> {
                joinRoom()
            }
            is JoinRoomContract.Event.OnScanQRCodeButtonClicked -> {
                scanQRCode()
            }
            is JoinRoomContract.Event.OnChangeCode -> {
                setState { copy(code = event.code) }
            }
            is JoinRoomContract.Event.OnChangeName -> {
                setState { copy(name = event.name) }
            }
            is JoinRoomContract.Event.OnClickShowQR -> {
                setState { copy(show = event.show) }
            }
            JoinRoomContract.Event.OnJoinRoom -> {

            }
            JoinRoomContract.Event.CloseSession -> {
                closeSession()
                setEffect { JoinRoomContract.Effect.NavigateToHome }
            }
        }
    }

    private fun observeData() {

        job = viewModelScope.launch(Dispatchers.IO) {

            joinRoomRepository.receiveData().collect { result ->
                when (result) {
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.cr_error_connection)) }
                    is BaseResult.Success ->{
                        val dataResponse = result.data.data
                        dataStoreRepository.saveGameCode(dataResponse.code)
                        dataStoreRepository.savePlayerId(dataResponse.id)
                        setEffect { JoinRoomContract.Effect.Navigate }
                        job?.cancel()
                    }

                    /**
                     * This line is Just for example
                     */
                    /*is BaseResult.Success -> setState {
                        copy(data = result.toString())
                    }*/

                }
            }
        }

    }

    private fun joinRoom() {
        viewModelScope.launch {
            val createJoinRoomRequest = JoinRoomDomain(
                    name = uiState.value.name,
                    code = uiState.value.code,
                )
            joinRoomRepository.joinRoom(createJoinRoomRequest)

        }
    }

    private fun closeSession(){
        viewModelScope.launch(Dispatchers.IO) {
            joinRoomRepository.closeSession()
        }
    }

    private fun scanQRCode() {
        TODO("Not yet implemented")
    }
}


fun String.toSafeIntString(): Int {
    val numberString = this.replace(" ", "")
    if (!this.isDigitsOnly()) return 0
    return if (numberString.isEmpty()) 0 else toInt()
}