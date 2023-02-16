package com.kk.presentation.baseMVI


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModelNoEvents< State: UiState, Effect : UiEffect> : ViewModel() {

    // Create Initial State of view
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    //For handling UiState we use STATEFLOW.
    // StateFlow is just like LiveData but have initial value.
    // So we have always a state. It is also a kind of SharedFlow.
    // We always want to receive last view state when UI become visible.

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    //for handling UiEffect we use CHANNELS.
    // Because Channels are hot and we do not need to show side
    // effect again when orientation changed or UI become visible again.
    // Simply we want to replicate SingleLiveEvent behavior.

    private val _effect : Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    // Get Current State
    val currentState: State get() = uiState.value

    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State){
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /**
     * Set new Effect
     */
    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
}