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

    data class State(
        val error: String? = null,
        val isLoading: Boolean = false,
        ): UiState

    sealed class HostState {
        object Idle : HostState()
        data class PreStartHost(val round: String) : HostState()
        data class AwaitingUsersAnswersHost(val round: String, var timeLeft: String, val body: String) : HostState()
        data class RatingAnswerHost(val round: String, var playerAnswer: String, var skipAnswer: Boolean): HostState()
        data class WinnerNameHost(val round: String, val winnerName: String): HostState()
        data class Error(val message: String) : HostState()
        data class Success(val value: Int) : HostState()
    }

    sealed class Effect : UiEffect {
        object NavigateWaitingAnswerHost : Effect()
    }
}