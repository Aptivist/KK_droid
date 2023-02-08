package com.kk.presentation

import androidx.lifecycle.viewModelScope
import com.kk.presentation.baseMVI.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelTest : BaseViewModel<ContractTest.Event, ContractTest.State, ContractTest.Effect>() {


    /**
     * Create initial State of Views
     */
    override fun createInitialState(): ContractTest.State {
        return ContractTest.State(
            ContractTest.RandomNumberState.Idle
        )
    }

    /**
     * Handle each event
     */
    override fun handleEvent(event: ContractTest.Event) {
        when (event) {
            is ContractTest.Event.OnRandomNumberClicked -> { generateRandomNumber() }
            is ContractTest.Event.OnShowToastClicked -> {
                setEffect { ContractTest.Effect.ShowToast }
            }
        }
    }


    /**
     * Generate a random number
     */
    private fun generateRandomNumber(){
        viewModelScope.launch {
            setState { copy(randomNumberState = ContractTest.RandomNumberState.Loading) }
            try {
                delay(3000)
                val random = (0..10).random()
                setState { copy(randomNumberState = ContractTest.RandomNumberState.Success(value = random)) }
            } catch (e: java.lang.Exception) {
                setEffect { ContractTest.Effect.ShowToast }
            }
        }
    }
}