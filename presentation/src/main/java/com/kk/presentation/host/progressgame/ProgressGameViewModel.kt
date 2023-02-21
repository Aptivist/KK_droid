package com.kk.presentation.host.progressgame

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kk.data.repository.ProgressGameRepository
import com.kk.domain.models.AddPointRequestDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.domain.models.PointsDomain
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProgressGameViewModel(private val progressGameRepository: ProgressGameRepository,
                            private val stringProvider: StringProvider)  :
    BaseViewModel<ContractProgressGame.Event, ContractProgressGame.State, ContractProgressGame.Effect>()  {

    private var job: Job? = null


    init {
        observeData()
    }
    override fun createInitialState(): ContractProgressGame.State {
        return ContractProgressGame.State(preStartState = true)
    }

    override fun handleEvent(event: ContractProgressGame.Event) {
        when (event) {
            is ContractProgressGame.Event.StartRound -> {
                startRound()
                setState { copy(preStartState = false, awaitingAnswersState = true) }
            }
            is ContractProgressGame.Event.RateAnswers -> {
            }
        }
    }

    private fun startRound(){
        viewModelScope.launch {
                val startRoundRequest = EventRequestDomain(
                    "START_ROUND"
                )
                progressGameRepository.startRound(startRoundRequest)
            }
    }



    private fun observeData(){
        job = viewModelScope.launch(Dispatchers.IO){
            progressGameRepository.receiveData().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.pg_error_message)) }
                    is BaseResult.Success -> {
                        setState { copy(timeLeft = result.data.data.time.toString()) }
                        if(result.data.status == "OK"){
                            if(uiState.value.timeLeft.toInt() < 1){
                                setEffect { ContractProgressGame.Effect.Navigate }
                                job?.cancel()
                            }
                        }
                    }
                }
            }
        }
    }
}