package com.kk.presentation.host.progressgame

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
    var job: Job? = null
    var round = 1
    var preStartState = false
    var awaitingAnswersState = false
    var winnerNameState = false
    init {
        observeData()
    }
    override fun createInitialState(): ContractProgressGame.State {
        preStartState = true
        return ContractProgressGame.State()
    }

    override fun handleEvent(event: ContractProgressGame.Event) {
        when (event) {
            is ContractProgressGame.Event.StartRound -> {
                preStartState = false

                startRound()
                awaitingAnswersState = true
            }

        }
    }

    private fun startRound(){
        viewModelScope.launch {
            viewModelScope.launch {
                val startRoundRequest = EventRequestDomain(
                    "START_ROUND"
                )
                progressGameRepository.startRound(startRoundRequest)
            }
        }
    }

    private fun correctAnswer(){
        viewModelScope.launch {
            viewModelScope.launch {
                val correctAnswerRequest = AddPointRequestDomain(
                    PointsDomain(
                        idPlayer = "GSGF001",
                        pointRequest = "ADD_POINT"
                    )
                )
                progressGameRepository.correctAnswer(correctAnswerRequest)
            }
        }
    }

    private fun noPoints(){
        viewModelScope.launch {
            viewModelScope.launch {
                val noPointsRequest  = EventRequestDomain(
                    "NO_POINTS"
                )
                progressGameRepository.noPoints(noPointsRequest)
            }
        }
    }

    private fun observeData(){
        job = viewModelScope.launch(Dispatchers.IO){
            progressGameRepository.receiveAnswers().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.pg_error_message)) }
                    is BaseResult.Success -> {}
                }
            }
        }
    }
}