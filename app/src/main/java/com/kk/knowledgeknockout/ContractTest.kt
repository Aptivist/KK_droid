package com.kk.knowledgeknockout

import com.kk.knowledgeknockout.base.UiEffect
import com.kk.knowledgeknockout.base.UiEvent
import com.kk.knowledgeknockout.base.UiState

class ContractTest {
    // Events that user performed
    sealed class Event : UiEvent {
        object OnRandomNumberClicked : Event()
        object OnShowToastClicked : Event() //Use when you need navigation
    }

    // UI View States
    data class State(val randomNumberState : RandomNumberState): UiState

    // View State
    sealed class RandomNumberState {
        object Idle : RandomNumberState()
        object Loading : RandomNumberState()
        data class Success(val value: Int) : RandomNumberState()
    }

    // Side Effect
    sealed class Effect : UiEffect { object ShowToast : Effect() }
}