package com.kk.presentation.player.gameroom.answer

import com.kk.domain.models.AnswerDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class UserAnswerContract {
    sealed class Event : UiEvent{
        object SendAnswer : Event()
    }

    data class State(val answerDomain: AnswerDomain) : UiState

    sealed class Effect : UiEffect{ object Navigate : Effect()}
}