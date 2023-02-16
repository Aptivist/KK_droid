package com.kk.presentation.host.creategame

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.kk.data.repository.CreateRoomRepository
import com.kk.domain.models.BaseResult
import com.kk.domain.models.CreateGameRequestDomain
import com.kk.domain.models.RulesDomain
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModel
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CreateRoomViewModel(private val createRoomRepository: CreateRoomRepository, private val stringProvider: StringProvider) :
    BaseViewModel<CreateRoomContract.Event, CreateRoomContract.State, CreateRoomContract.Effect>() {
    private var job: Job? = null
    init {
        observeData()
    }

    override fun createInitialState(): CreateRoomContract.State {
        return CreateRoomContract.State()
    }

    override fun handleEvent(event: CreateRoomContract.Event) {

        when (event) {
            is CreateRoomContract.Event.OnChangePlayers -> {
                setState { copy(players = event.players.toSafeIntDigit()) }
            }
            is CreateRoomContract.Event.OnChangePoints -> {
                setState { copy(points = event.points.toSafeIntDigit()) }
            }
            is CreateRoomContract.Event.OnChangeTime -> {
                setState { copy(time = event.time.toSafeIntDigit()) }
            }
            CreateRoomContract.Event.OnCreateRoom -> {
                createRoom()
            }

        }
    }

    private fun createRoom() {
            viewModelScope.launch {
                val createGameRequest = CreateGameRequestDomain(
                    RulesDomain(
                        maxPlayers = uiState.value.players,
                        points = uiState.value.points,
                        timerSeconds = uiState.value.time
                    )
                )
                createRoomRepository.createRoom(createGameRequest)
            }

    }

    private fun observeData() {

        job = viewModelScope.launch(Dispatchers.IO) {
            createRoomRepository.receiveData().collect { result ->
                when (result) {
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.cr_error_connection)) }
                    is BaseResult.Success ->{
                        setState { copy(code= result.data.data.code) }
                        setEffect { CreateRoomContract.Effect.Navigate }
                        job?.cancel()
                    }

                    /**
                     * This line is Just for example
                     */
                    /*is BaseResult.Success -> setState {
                        copy(data = result.toString())
                    }*/

                }
            }
        }

    }

}

fun String.toSafeIntDigit(): Int {
    val numberString = this.replace(" ", "")
    if (!this.isDigitsOnly()) return 0
    return if (numberString.isEmpty()) 0 else toInt()
}