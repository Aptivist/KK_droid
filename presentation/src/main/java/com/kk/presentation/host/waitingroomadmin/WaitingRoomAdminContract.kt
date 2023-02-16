package com.kk.presentation.host.waitingroomadmin

import com.kk.domain.models.PlayerUserDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState
import com.kk.presentation.host.creategame.CreateRoomContract

class WaitingRoomAdminContract {
    sealed class Event : UiEvent{
        object showPlayers : Event()
    }

    data class State(val players : List<PlayerUserDomain> = emptyList(), val error : String? = null) : UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}