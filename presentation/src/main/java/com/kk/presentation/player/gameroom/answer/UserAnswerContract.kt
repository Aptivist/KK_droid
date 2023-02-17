package com.kk.presentation.player.gameroom.answer

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent

class UserAnswerContract {
    sealed class Event : UiEvent{
        object SendAnswer : Event()
    }

    data class State(val )

    sealed class Effect : UiEffect{ object Navigate : Effect()}
}