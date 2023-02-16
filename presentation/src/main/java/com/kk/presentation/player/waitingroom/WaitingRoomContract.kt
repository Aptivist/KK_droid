package com.kk.presentation.player.waitingroom

import com.kk.domain.models.PlayerUserDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class WaitingRoomContract {
    sealed class Event : UiEvent

    data class State(val playerList: List<PlayerUserDomain> = emptyList(), val error: String? = null) : UiState

    sealed class Effect : UiEffect

}