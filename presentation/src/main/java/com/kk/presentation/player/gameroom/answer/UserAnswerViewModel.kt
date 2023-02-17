package com.kk.presentation.player.gameroom.answer

import com.kk.data.repository.UserAnswerRepository
import com.kk.presentation.baseMVI.BaseViewModel

class UserAnswerViewModel(private val userAnswerRepository: UserAnswerRepository)
    : BaseViewModel<
        UserAnswerContract.Effect,
        UserAnswerContract.State,
        UserAnswerContract.Event >() {

    override fun createInitialState(): UserAnswerContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: UserAnswerContract.Effect) {
        TODO("Not yet implemented")
    }
}