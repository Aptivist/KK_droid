package com.kk.presentation

import com.kk.presentation.baseMVI.UiEffect
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

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