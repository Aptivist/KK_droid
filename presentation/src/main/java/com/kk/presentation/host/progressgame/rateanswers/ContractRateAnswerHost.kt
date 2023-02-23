package com.kk.presentation.host.progressgame.rateanswers

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ContractRateAnswerHost {

    sealed class Event : UiEvent {
        object CorrectAnswer: Event()
        object IncorrectAnswer : Event()
        object SkipAnswers: Event()
    }

    data class State(
        val error: String? = null,
        var round: Int = 1,
        var playerAnswer: String = "",
        var skipAnswers: Boolean = false,
    ): UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}