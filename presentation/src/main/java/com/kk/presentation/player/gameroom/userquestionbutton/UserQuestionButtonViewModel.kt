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
        // pending testing to get the current timer from host for example 45 seconds
//        observeData()
    }

    override fun createInitialState(): UserQuestionButtonContract.State {
        return UserQuestionButtonContract.State(isLoading = false)
    }

    override fun handleEvent(event: UserQuestionButtonContract.Event) {
        when (event) {
            is UserQuestionButtonContract.Event.OnMainButtonClicked -> {
                setState { copy(timeStamp = System.currentTimeMillis().toInt()) }
                setEffect { UserQuestionButtonContract.Effect.NavigateToSendPlayerAnswer }
            }
            is UserQuestionButtonContract.Event.OnSkipButtonClicked -> {}
        }
    }

    private fun observeData() {
        job = viewModelScope.launch(Dispatchers.IO) {
            userQuestionButtonRepository.receiveData().collect { result ->
                when (result) {
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.cr_error_connection)) }
                    is BaseResult.Success -> {
                        Log.e("Timer", result.toString())
                        setState { copy(timer = result.data.data.time) }
                    }
                }
            }
        }
    }
}