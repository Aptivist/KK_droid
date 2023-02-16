package com.kk.presentation.player.resultroom

import com.kk.domain.models.GameResultDomain
import com.kk.domain.models.PlayerUserDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ResultRoomContract {

    sealed class Event : UiEvent {
        object OnGoHomeClicked : Event()
    }

    data class State(
        val status: String? = null,
        val data: GameResultDomain = GameResultDomain(),
        val error: String? = null
    ): UiState

    sealed class Effect : UiEffect {
        object Navigate : Effect()
    }

}