package com.kk.presentation.host.progressgame.rateanswers

import com.kk.domain.models.PlayerAnswerDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ContractRateAnswerHost {

    sealed class Event : UiEvent {
        object ReceiveAnswers : Event()
        object NoAnswers : Event()
        object CorrectAnswer: Event()
        object IncorrectAnswer : Event()
    }

    data class State(
        val error: String? = null,
        var round: Int = 1,
        val answers: List<PlayerAnswerDomain> = emptyList(),
        var idWinner: String = "",
        var playerAnswer: String = "",
        var correctAnswer: Boolean = false,
        var incorrectAnswer: Boolean = false,
        val skipAnswer: Boolean = false
    ): UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}