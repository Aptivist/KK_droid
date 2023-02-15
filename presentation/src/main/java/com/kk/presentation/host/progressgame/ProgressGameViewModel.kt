package com.kk.presentation.host.progressgame

import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ProgressGameViewModel :
    BaseViewModel<ContractProgressGame.Event, ContractProgressGame.State, ContractProgressGame.Effect>()  {

    lateinit var round: String
    lateinit var timeLeft: String
    lateinit var playersAnswers: Map<String, String>
    lateinit var playersNames: Map<String, String>
    override fun createInitialState(): ContractProgressGame.State {
        return ContractProgressGame.State(isLoading = false)
    }

    override fun handleEvent(event: ContractProgressGame.Event) {
        when (event) {
            is ContractProgressGame.Event.StartRound -> {}
            is ContractProgressGame.Event.CorrectAnswer -> {}
            is ContractProgressGame.Event.IncorrectAnswer -> {}
            is ContractProgressGame.Event.SkipAnswer -> {}
            is ContractProgressGame.Event.NextRound -> {}
        }
    }
}