package com.kk.presentation.player.gameroom.userquestionbutton

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.UserQuestionButtonRepository
import com.kk.domain.models.BaseResult
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserQuestionButtonViewModel(
    private val userQuestionButtonRepository: UserQuestionButtonRepository,
    private val stringProvider: StringProvider
) :
    BaseViewModel<UserQuestionButtonContract.Event, UserQuestionButtonContract.State, UserQuestionButtonContract.Effect>() {

    init {
        observeData()
    }

    override fun createInitialState(): UserQuestionButtonContract.State {
        return UserQuestionButtonContract.State(isLoading = false)
    }

    override fun handleEvent(event: UserQuestionButtonContract.Event) {
        when (event) {
            is UserQuestionButtonContract.Event.OnMainButtonClicked -> {
                setState { copy(timeStamp = System.currentTimeMillis().toInt()) }
            }
            is UserQuestionButtonContract.Event.OnSkipButtonClicked -> {}
        }
    }

    private fun observeData() {
        viewModelScope.launch(Dispatchers.IO) {
            userQuestionButtonRepository.receiveData().collect { result ->
                when (result) {
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.cr_error_connection)) }
                    is BaseResult.Success -> setState { copy(baseResponseDomain = result.data) }
                }
            }
        }
    }

}