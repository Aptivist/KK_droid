package com.kk.presentation.host.progressgame.rateanswers

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kk.data.repository.RateAnswerRepository
import com.kk.domain.models.*
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
    private lateinit var answerList : Array<PlayerAnswerDomain>
    private var currentAnswerIndex = 0
    init {
        observeData()
        showAnswers()
    }
    override fun createInitialState(): ContractRateAnswerHost.State {
        return ContractRateAnswerHost.State()
    }

    override fun handleEvent(event: ContractRateAnswerHost.Event) {
        when (event) {
            is ContractRateAnswerHost.Event.CorrectAnswer -> {
                correctAnswer()
            }
            is ContractRateAnswerHost.Event.IncorrectAnswer -> {
                incorrectAnswer()
            }
        }
    }

    private fun incorrectAnswer() {
        currentAnswerIndex++
        if(currentAnswerIndex < answerList.size){
            setState { copy(playerAnswer = answerList[currentAnswerIndex].answer) }
        }else{
            noPoints()
        }
    }

    private fun observeData(){
        job = viewModelScope.launch(Dispatchers.IO){
            rateAnswerRepository.receiveAnswers().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.pg_error_message)) }
                    is BaseResult.Success -> {
                        when (result.data.status){
                            "OK" -> {
                                answerList = result.data.data.toTypedArray()
                                setState { copy(playerAnswer = answerList[currentAnswerIndex].answer) }
                            }
                            "NO_ANSWERS" -> {
                                setEffect { ContractRateAnswerHost.Effect.Navigate }
                                job?.cancel()
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }



    private fun correctAnswer(){
        viewModelScope.launch {
            val correctAnswerRequest = AddPointRequestDomain(
                PointsDomain(
                    answerList[currentAnswerIndex].playerId,
                    "ADD_POINT"
                )
            )
            rateAnswerRepository.addPoint(correctAnswerRequest)
            setEffect { ContractRateAnswerHost.Effect.Navigate }
            job?.cancel()
        }
    }

    private fun noPoints(){
        viewModelScope.launch {
            val noPointsRequest = EventRequestDomain(
                "NO_POINTS"
            )
            rateAnswerRepository.noPoints(noPointsRequest)
            setEffect { ContractRateAnswerHost.Effect.Navigate }
            job?.cancel()
        }
    }

    private fun showAnswers(){
        viewModelScope.launch {
            rateAnswerRepository.showAnswers(EventRequestDomain("SHOW_ANSWERS"))
        }
    }
}