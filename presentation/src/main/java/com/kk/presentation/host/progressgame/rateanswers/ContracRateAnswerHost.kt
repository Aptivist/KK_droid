package com.kk.presentation.host.progressgame.rateanswers

import com.kk.domain.models.PlayersAnswerDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ContracRateAnswerHost {

    sealed class Event : UiEvent {
        object ReceiveAnswers : Event()
        object NoAnswers : Event()
        object CorrectAnswer: Event()
        object IncorrectAnswer : Event()
    }

    data class State(
        val error: String? = null,
        val round: Int = 1,
        val answers: List<PlayersAnswerDomain> = emptyList()
    ): UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}