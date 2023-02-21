package com.kk.presentation.host.progressgame.rateanswers

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kk.data.repository.RateAnswerRepository
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

class RateAnswerHostViewModel(private val rateAnswerRepository: RateAnswerRepository,
                              private val stringProvider: StringProvider):
    BaseViewModel<ContractRateAnswerHost.Event, ContractRateAnswerHost.State, ContractRateAnswerHost.Effect>(){

    private var job: Job? = null
    init {
        observeData()
        showAnswers()
    }
    override fun createInitialState(): ContractRateAnswerHost.State {
        return ContractRateAnswerHost.State()
    }

    override fun handleEvent(event: ContractRateAnswerHost.Event) {
        when (event) {
            is ContractRateAnswerHost.Event.ReceiveAnswers -> {

            }
            is ContractRateAnswerHost.Event.NoAnswers -> {
                noPoints()
            }
            is ContractRateAnswerHost.Event.CorrectAnswer -> {
                Log.e("CHECKING....","Correct answer")
                setState { copy(correctAnswer = true, incorrectAnswer = false) }
                correctAnswer()
            }
            is ContractRateAnswerHost.Event.IncorrectAnswer -> {
                Log.e("CHECKING....","Incorrect answer")
                setState { copy(correctAnswer = false, incorrectAnswer = true) }
            }
        }
    }

    private fun observeData(){
        job = viewModelScope.launch(Dispatchers.IO){
            rateAnswerRepository.receiveAnswers().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.pg_error_message)) }
                    is BaseResult.Success -> {
                        setState { copy(answers = result.data.data) }
                        if (result.data.status == "OK"){
                            if(rateAnswersList()){
                                //setEffect { ContractRateAnswerHost.Effect.Navigate }
                                //job?.cancel()
                            }
                        }
                        if (result.data.status == "NO_ANSWERS"){
                            setEffect { ContractRateAnswerHost.Effect.Navigate }
                            job?.cancel()
                        }
                    }
                }
            }
        }
    }

    private fun rateAnswersList(): Boolean{
        val answers = uiState.value.answers
        var isAnyCorrectAns = false
        for (userAnswer in answers){
            setState { copy(idWinner = userAnswer.playerId, playerAnswer = userAnswer.answer) }
            if (uiState.value.correctAnswer and !uiState.value.incorrectAnswer){
                isAnyCorrectAns = true
                break
            }
            if (!uiState.value.correctAnswer and uiState.value.incorrectAnswer){
                setState { copy(idWinner = "") }
            }
        }
        return isAnyCorrectAns
    }

    private fun correctAnswer(){
        viewModelScope.launch {
            val correctAnswerRequest = AddPointRequestDomain(
                PointsDomain(
                    uiState.value.idWinner,
                    "ADD_POINT"
                )
            )
            rateAnswerRepository.addPoint(correctAnswerRequest)
        }
    }

    private fun noPoints(){
        viewModelScope.launch {
            val noPointsRequest = EventRequestDomain(
                "NO_POINTS"
            )
            rateAnswerRepository.noPoints(noPointsRequest)
        }
    }

    private fun showAnswers(){
        viewModelScope.launch {
            rateAnswerRepository.showAnswers(EventRequestDomain("SHOW_ANSWERS"))
        }
    }
}