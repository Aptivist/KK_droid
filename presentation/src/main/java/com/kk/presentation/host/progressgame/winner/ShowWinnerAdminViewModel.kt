package com.kk.presentation.host.progressgame.winner

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kk.data.repository.WinnerAdminRepository
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ShowWinnerAdminViewModel(private val winnerAdminRepository: WinnerAdminRepository,
                               private val stringProvider: StringProvider) :
BaseViewModel<ContractShowWinnerAdmin.Event, ContractShowWinnerAdmin.State, ContractShowWinnerAdmin.Effect>() {
    private var job: Job? = null

    init {
        observeData()
    }

    override fun createInitialState(): ContractShowWinnerAdmin.State {
        return ContractShowWinnerAdmin.State()
    }

    override fun handleEvent(event: ContractShowWinnerAdmin.Event) {
        when (event) {
            is ContractShowWinnerAdmin.Event.ShowWinnerName -> {}
            is ContractShowWinnerAdmin.Event.NextGame -> {
                nextRound()
                setEffect { ContractShowWinnerAdmin.Effect.Navigate }
            }
        }
    }

    private fun nextRound() {
        viewModelScope.launch {
            val nextRoundRequest = EventRequestDomain(
                "NEXT_ROUND"
            )
            winnerAdminRepository.startNextGame(nextRoundRequest)
        }
    }

    private fun observeData() {
        job = viewModelScope.launch(Dispatchers.IO){
            winnerAdminRepository.receiveWinner().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.pg_error_message)) }
                    is BaseResult.Success -> {
                        when (result.data.status){
                            "ROUND_FINISHED" -> {
                                if(result.data.data.listPlayers.isNullOrEmpty()){
                                    setState { copy(anyWinner = true, winnerName = result.data.data.roundPlayerWon.name) }
                                    job?.cancel()
                                }
                                if(!result.data.data.listPlayers.isNullOrEmpty()){

                                }
                            }
                            "GAME_FINISHED" -> {
                                setState { copy(gameWinner = true, winnerName = result.data.data.roundPlayerWon.name) }
                                job?.cancel()
                            }
                            "NO_WINNER_ROUND" -> {
                                setState { copy(noWinner = true, anyWinner = false, gameWinner = false) }
                                job?.cancel()
                            }
                        }
                    }
                }
            }
        }
    }
}