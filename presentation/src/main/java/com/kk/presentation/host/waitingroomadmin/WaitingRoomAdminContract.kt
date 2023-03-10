package com.kk.presentation.host.waitingroomadmin

import android.graphics.Bitmap
import com.kk.domain.models.PlayerUserDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class WaitingRoomAdminContract {
    sealed class Event : UiEvent{
        object OnStartGame : Event()
    }

    data class State(
        val players: List<PlayerUserDomain> = emptyList(),
        val codeQR: Bitmap? = null,
        val error: String? = null
    ) : UiState

    sealed class Effect : UiEffect { object Navigate : Effect() }
}