package com.kk.presentation.player.resultroom

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.ResultGameRepository
import com.kk.domain.models.BaseResult
import com.kk.local.domain.PreferencesRepository
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ResultRoomViewModel(
    private val resultGameRepository: ResultGameRepository,
    private val stringProvider: StringProvider,
    private val dataStoreRepository: PreferencesRepository,
) : BaseViewModel<ResultRoomContract.Event, ResultRoomContract.State, ResultRoomContract.Effect>() {

    private var job: Job? = null
    private var playerId = ""
    private var roundNumber = 0

    init {
        getRoundNumber()
        getPlayerId()
        observeData()
        changeRoundNumber()
    }

    override fun createInitialState(): ResultRoomContract.State {
        //TODO("Add TO Resource files")
        return ResultRoomContract.State()
    }

    override fun handleEvent(event: ResultRoomContract.Event) {
        when (event) {
            ResultRoomContract.Event.OnGoHomeClicked -> {
                closeSession()
                setEffect { ResultRoomContract.Effect.NavigateToHome }
            }
            ResultRoomContract.Event.CloseSession -> {
                closeSession()
                deleteLocalData()
                setEffect {
                    ResultRoomContract.Effect.NavigateToHome
                }
            }
        }
    }

    private fun observeData() {
        job = viewModelScope.launch(Dispatchers.IO) {
            resultGameRepository.receiveData().collect() { result ->
                when (result) {
                    is BaseResult.Error -> {
                        setState {
                            copy(error = stringProvider.getString(R.string.cr_error_connection))
                        }
                    }
                    is BaseResult.Success -> {
                        // TODO REFACTOR
                        val gameResult = result.data

                        if (gameResult.status == "NEXT_ROUND") {
                            setEffect { ResultRoomContract.Effect.NavigateToNextRound }
                            job?.cancel()
                        } else {
                            if (gameResult.status == "GAME_FINISHED") {
                                setState {
                                    //TODO (ADD correct title)
                                    copy(
                                        status = if (playerId == gameResult.data.roundPlayerWon?.id) "GAME_FINISHED_WON" else "GAME_FINISHED_LOSE",
                                        title = stringProvider.getString(R.string.results),
                                        players = gameResult.data.listPlayers
                                    )

                                }
                            } else {
                                setState {
                                    //TODO (ADD correct title)
                                    copy(
                                        status = gameResult.status,
                                        title = stringProvider.getString(R.string.results),
                                        winnerName = gameResult.data.roundPlayerWon?.name ?: "",
                                        players = gameResult.data.listPlayers
                                    )

                                }
                            }

                        }

                    }
                }

            }
        }
    }

    private fun getPlayerId() {
        viewModelScope.launch(Dispatchers.IO) {
            playerId = dataStoreRepository.getPlayerId()
        }
    }

    private fun closeSession() {
        viewModelScope.launch(Dispatchers.IO) {
            resultGameRepository.closeSession()
        }
    }

    private fun deleteLocalData() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.clearPreferences()
        }
    }

    private fun getRoundNumber(){
        viewModelScope.launch(Dispatchers.IO) {
            roundNumber = dataStoreRepository.getNumberRound()
            setState { copy(title = stringProvider.getString(R.string.round_n, roundNumber)) }
        }
    }

    private fun changeRoundNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            roundNumber += 1
            dataStoreRepository.saveNumberRound(roundNumber)
        }
    }
}