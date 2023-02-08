package com.kk.knowledgeknockout

import androidx.lifecycle.viewModelScope
import com.kk.knowledgeknockout.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelTest : BaseViewModel<ContractTest.Event, ContractTest.State, ContractTest.Effect>() {

    //Create initial state of views
    override fun createInitialState(): ContractTest.State {
        return ContractTest.State(
            ContractTest.RandomNumberState.Idle
        )
    }

    /**
     * Handle each event
     */
    override fun handleEvent(event: ContractTest.Event) {
        when(event){
            is ContractTest.Event.OnRandomNumberClicked -> { generateRandomNumber() }
                ContractTest.Event.OnShowToastClicked -> {
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