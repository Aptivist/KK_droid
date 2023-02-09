package com.kk.presentation.host.progressgame

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ContractProgressGame {
    sealed class Event : UiEvent {
        object StartRound : Event()
        object CorrectAnswer : Event()
        object IncorrectAnswer : Event()
        object SkipAnswer : Event()
        object NextRound : Event()
    }

    data class State(val hostState: HostState): UiState

    sealed class HostState {
        object Idle : HostState()
        object PreStartHost : HostState()
        object AwaitingUsersAnswersHost : HostState()
        object RatingAnswerHost: HostState()
        object WinnerNameHost: HostState()
        data class Success(val value: Int) : HostState()
    }

    sealed class Effect : UiEffect { }
}