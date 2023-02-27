package com.kk.presentation.player.gameroom

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.AnswerRepository
import com.kk.domain.models.AnswerDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventAnswerRequestDomain
import com.kk.local.domain.PreferencesRepository
import com.kk.presentation.baseMVI.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnswerViewModel(
    private val answerRepository: AnswerRepository,
    private val preferencesRepository: PreferencesRepository
) : BaseViewModel<AnswerContract.Event, AnswerContract.State, AnswerContract.Effect>() {

    private lateinit var playerId: String
    private lateinit var gameCode: String
    private var job: Job? = null

    init {
        getPlayerId()
        getGameCode()
        getRoundNumber()
        observeData()
    }

    override fun createInitialState(): AnswerContract.State {
        return AnswerContract.State()
    }

    override fun handleEvent(event: AnswerContract.Event) {
        when (event) {
            is AnswerContract.Event.OnChangeAnswer -> {
                setState { copy(answer = event.answer) }
            }
            is AnswerContract.Event.SendAnswer -> {
                sendAnswer(timeStamp = event.timeStamp)
            }
            AnswerContract.Event.CloseSession -> {
                closeSession()
                setEffect { AnswerContract.Effect.NavigateToHome }
            }
        }
    }

    private fun sendAnswer(timeStamp: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            answerRepository.sendAnswer(
                EventAnswerRequestDomain(
                    answerDomain = AnswerDomain(
                        answer = uiState.value.answer,
                        timeStamp = timeStamp,
                        gameCode = gameCode,
                        playerId = playerId
                    )
                )
            )
            setState { copy(showTexFieldAndButton = false) }
        }

    }

    private fun closeSession(){
        viewModelScope.launch(Dispatchers.IO){
            answerRepository.closeSession()
        }
    }

    private fun observeData() {
        job = viewModelScope.launch(Dispatchers.IO) {
            answerRepository.receiveData().collect{ result ->
                when(result){
                    is BaseResult.Error -> {}
                    is BaseResult.Success -> {
                        val timer = result.data.data.time.toString()
                        setState { copy(timer = timer ) }
                        if(result.data.status == "OK"){
                            if(timer.toInt() < 1){
                                setEffect { AnswerContract.Effect.NavigateToResults }
                                job?.cancel()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getPlayerId() {
        viewModelScope.launch(Dispatchers.IO) {
            playerId = preferencesRepository.getPlayerId()
        }
    }

    private fun getGameCode() {
        viewModelScope.launch(Dispatchers.IO) {
            gameCode = preferencesRepository.getGameCode()
        }
    }

    private fun getRoundNumber(){
        viewModelScope.launch(Dispatchers.IO) {
            val roundNumber = preferencesRepository.getNumberRound()
            setState { copy(roundNumber = roundNumber) }
        }
    }

}