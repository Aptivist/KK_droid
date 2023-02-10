package com.kk.presentation.host.progressgame

import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.baseMVI.UiEvent
import com.kk.presentation.baseMVI.UiState

class ProgressGameViewModel : BaseViewModel<ContractProgressGame.Event, ContractProgressGame.State, ContractProgressGame.Effect>()  {
    override fun createInitialState(): ContractProgressGame.State {
        return ContractProgressGame.State(
            ContractProgressGame.HostState.Idle
        )
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