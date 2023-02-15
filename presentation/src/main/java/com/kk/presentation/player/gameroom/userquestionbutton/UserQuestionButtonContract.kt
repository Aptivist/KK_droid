package com.kk.presentation.player.gameroom.userquestionbutton

import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.KKTimerDomain
import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class UserQuestionButtonContract {
    // Events that user performed
    sealed class Event : UiEvent {
        data class OnMainButtonClicked(val timeStamp: Long = 0) : Event()
        object OnSkipButtonClicked : Event()
    }

    // View State
    data class State(
        val round: Int = 0,
        val timeStamp: Int = 0,
        val baseResponseDomain: BaseResponseDomain<KKTimerDomain> = BaseResponseDomain(
            status = "IDLE",
            KKTimerDomain(0)
        ),
        val error: String? = null,
        val isLoading: Boolean = false,
    ) : UiState

    // Side Effect
    sealed class Effect : UiEffect {
        object NavigateToSendPlayerAnswer : Effect()
        object NavigateToWaitingPlayers : Effect()
    }
}