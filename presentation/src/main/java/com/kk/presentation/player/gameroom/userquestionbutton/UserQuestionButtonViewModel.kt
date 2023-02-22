package com.kk.presentation.player.gameroom.userquestionbutton

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kk.data.repository.UserQuestionButtonRepository
import com.kk.domain.models.BaseResult
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserQuestionButtonViewModel(
    private val userQuestionButtonRepository: UserQuestionButtonRepository,
    private val stringProvider: StringProvider
) :
    BaseViewModel<UserQuestionButtonContract.Event, UserQuestionButtonContract.State, UserQuestionButtonContract.Effect>() {

    var job: Job? = null

    init {
        observeData()
    }

    override fun createInitialState(): UserQuestionButtonContract.State {
        return UserQuestionButtonContract.State(roundStarted = false)
    }

    override fun handleEvent(event: UserQuestionButtonContract.Event) {
        when (event) {
            is UserQuestionButtonContract.Event.OnMainButtonClicked -> {
                setState { copy(timeStamp = System.currentTimeMillis()) }
                setEffect { UserQuestionButtonContract.Effect.NavigateToSendPlayerAnswer }
                job?.cancel()
            }
            is UserQuestionButtonContract.Event.OnSkipButtonClicked -> {}
            UserQuestionButtonContract.Event.CloseSession -> {
                closeSession()
                setEffect { UserQuestionButtonContract.Effect.NavigateToHome }
            }
        }
    }

    private fun observeData() {
        job = viewModelScope.launch(Dispatchers.IO) {
            userQuestionButtonRepository.receiveData().collect { result ->
                Log.e("log", result.toString())
                when (result) {
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.cr_error_connection)) }
                    is BaseResult.Success -> {
                        if (result.data.data.time > 0) {
                            setState { copy(zIndex = 2f) }
                            setState { copy(timer = result.data.data.time) }
                            setState { copy(roundStarted = true) }
                        } else if (result.data.data.time == 0) {
                            setState { copy(zIndex = 0f) }
                            setState { copy(timer = 0) }
                            setState { copy(roundStarted = false) }
                            setEffect { UserQuestionButtonContract.Effect.NavigateToWaitingPlayers }
                            job?.cancel()
                        }
                    }
                }
            }
        }
    }

    private fun closeSession(){
        viewModelScope.launch(Dispatchers.IO){
            userQuestionButtonRepository.closeSession()
        }
    }
}