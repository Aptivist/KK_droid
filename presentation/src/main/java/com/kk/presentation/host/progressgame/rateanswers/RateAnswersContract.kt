package com.kk.presentation.host.progressgame.rateanswers

import com.kk.presentation.baseMVI.UiEvent

class RateAnswersContract {
    sealed class Event : UiEvent{
        data class receiveAnswers(): Event()
    }
}