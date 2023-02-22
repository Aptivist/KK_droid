package com.kk.presentation.player.resultroom

import androidx.lifecycle.viewModelScope
import com.kk.domain.models.BaseResult
import com.kk.data.repository.ResultGameRepository
import com.kk.local.domain.PreferencesRepository
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultRoomViewModel(
    private val resultGameRepository: ResultGameRepository,
    private val stringProvider: StringProvider,
    private val dataStoreRepository: PreferencesRepository,
) : BaseViewModel<ResultRoomContract.Event, ResultRoomContract.State, ResultRoomContract.Effect>() {
    init {
        observeData()
    }
    override fun createInitialState(): ResultRoomContract.State {
        return ResultRoomContract.State()
    }

    override fun handleEvent(event: ResultRoomContract.Event) {
        when (event) {
            ResultRoomContract.Event.OnGoHomeClicked -> {
                setEffect { ResultRoomContract.Effect.Navigate }
            }
            ResultRoomContract.Event.CloseSession -> {
                setEffect {
                    ResultRoomContract.Effect.Navigate
                }
                closeSession()
                deleteLocalData()
            }
        }
    }

    private fun observeData() {
        viewModelScope.launch(Dispatchers.IO) {
            resultGameRepository.receiveData().collect() { result ->
                when(result) {
                    is BaseResult.Error -> {
                        setState {
                            copy(error = stringProvider.getString(R.string.cr_error_connection))
                        }
                    }
                    is BaseResult.Success -> setState {
                        copy(data = result.data.data, status = result.data.status)
                    }
                }

            }
        }
    }

    private fun closeSession(){
        viewModelScope.launch(Dispatchers.IO){
            resultGameRepository.closeSession()
        }
    }

    private fun deleteLocalData(){
        viewModelScope.launch(Dispatchers.IO){
            dataStoreRepository.clearPreferences()
        }
    }
}