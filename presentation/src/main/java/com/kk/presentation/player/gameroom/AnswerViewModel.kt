package com.kk.presentation.player.gameroom

import androidx.lifecycle.viewModelScope
import com.kk.presentation.baseMVI.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnswerViewModel: BaseViewModel<AnswerContract.Event,AnswerContract.State,AnswerContract.Effect>() {

    init {
        observeData()
    }
    override fun createInitialState(): AnswerContract.State {
        return AnswerContract.State()
    }

    override fun handleEvent(event: AnswerContract.Event) {
        when(event){
            AnswerContract.Event.SendAnswer -> {}
        }
    }

    private fun observeData(){
        viewModelScope.launch(Dispatchers.IO){

        }
    }
}