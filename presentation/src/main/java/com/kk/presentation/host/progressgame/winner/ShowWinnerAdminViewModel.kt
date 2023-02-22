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
            }
        }
    }

    private fun observeData() {
        job = viewModelScope.launch(Dispatchers.IO){
            winnerAdminRepository.receiveWinner().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.pg_error_message)) }
                    is BaseResult.Success -> {
                        Log.e("CHECKING......", result.data.status.toString())
                        when (result.data.status){
                            "ROUND_FINISHED" -> {
                                setState { copy(anyWinner = true, winnerName = result.data.data.name) }
                                job?.cancel()
                            }
                            "INITIALIZED" -> {
                                setEffect { ContractShowWinnerAdmin.Effect.Navigate }
                                job?.cancel()
                            }
                            else -> {
                                setState { copy(anyWinner = false, noWinner = true) }
                                job?.cancel()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun nextRound() {
        viewModelScope.launch {
            val nextRoundRequest = EventRequestDomain(
                "START_GAME"
            )
            winnerAdminRepository.startNextGame(nextRoundRequest)
            setEffect { ContractShowWinnerAdmin.Effect.Navigate }
        }
    }
}