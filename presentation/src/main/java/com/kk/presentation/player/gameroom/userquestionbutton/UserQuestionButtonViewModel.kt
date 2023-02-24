package com.kk.presentation.player.gameroom.userquestionbutton

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.UserQuestionButtonRepository
import com.kk.domain.models.BaseResult
import com.kk.local.domain.PreferencesRepository
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserQuestionButtonViewModel(
    private val userQuestionButtonRepository: UserQuestionButtonRepository,
    private val dataStoreRepository: PreferencesRepository,
    private val stringProvider: StringProvider
) :
    BaseViewModel<UserQuestionButtonContract.Event, UserQuestionButtonContract.State, UserQuestionButtonContract.Effect>() {

    var job: Job? = null

    init {
        observeData()
        getRoundNumber()
    }

    override fun createInitialState(): UserQuestionButtonContract.State {
        return UserQuestionButtonContract.State()
    }

    override fun handleEvent(event: UserQuestionButtonContract.Event) {
        when (event) {
            is UserQuestionButtonContract.Event.OnMainButtonClicked -> {
                setState { copy(timeStamp = System.currentTimeMillis()) }
                setEffect { UserQuestionButtonContract.Effect.NavigateToSendPlayerAnswer }
                job?.cancel()
            }
            is UserQuestionButtonContract.Event.OnSkipButtonClicked -> {
                setState { copy(message = stringProvider.getString(R.string.uqb_skipping)) }
                setState { copy(zIndex = 0f) }
                setState { copy(skipped = true) }
            }
            UserQuestionButtonContract.Event.CloseSession -> {
                setState { copy(showDialog = false) }
                endGame()
                setEffect { UserQuestionButtonContract.Effect.NavigateToHome }
                job?.cancel()
            }
        }
    }

    private fun observeData() {
        job = viewModelScope.launch(Dispatchers.IO) {
            userQuestionButtonRepository.receiveData().collect { result ->
                when (result) {
                    is BaseResult.Error -> {
                        setState { copy(showDialog = true) }
                        setState { copy(error = stringProvider.getString(R.string.cr_error_connection)) }
                    }
                    is BaseResult.Success -> {
                        if (result.data.data.time > 0 && uiState.value.skipped.not()) {
                            setState { copy(zIndex = 2f) }
                        } else if (result.data.data.time == 0) {
                            navigateToWaitingPlayers()
                        }
                        setState { copy(timer = result.data.data.time) }
                    }
                }
            }
        }
    }

    private fun navigateToWaitingPlayers() {
        setState { copy(zIndex = 0f) }
        setState { copy(timer = 0) }
        setEffect { UserQuestionButtonContract.Effect.NavigateToResults }
        job?.cancel()
    }

    private fun endGame() {
        viewModelScope.launch(Dispatchers.IO) {
            userQuestionButtonRepository.closeSession()
            dataStoreRepository.clearPreferences()
        }
    }

    private fun getRoundNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            var roundNumber = dataStoreRepository.getNumberRound()
            if (roundNumber == 0) {
                roundNumber = 1
                dataStoreRepository.saveNumberRound(roundNumber)
            }
            setState { copy(round = roundNumber) }
        }
    }
}