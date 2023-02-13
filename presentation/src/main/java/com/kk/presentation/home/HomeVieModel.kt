package com.kk.presentation.home

import androidx.lifecycle.viewModelScope
import com.kk.data.ISocketService
import com.kk.data.UserType
import com.kk.domain.models.BaseResult
import com.kk.presentation.baseMVI.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVieModel(private val socketService: ISocketService) :
    BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    override fun createInitialState(): HomeContract.State {
        return HomeContract.State(isLoading = false)
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.OnConnectHost -> {
                setState { HomeContract.State(isLoading = true) }
                connectSocket(UserType.HostType)
            }
            HomeContract.Event.OnConnectPlayer -> {
                setState { HomeContract.State(isLoading = true) }
                connectSocket(UserType.PlayerType)
            }

            HomeContract.Event.OnRestartSate -> {
                setState { HomeContract.State(isLoading = false, error = null) }
            }
        }
    }

    private fun connectSocket(userType: UserType) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = socketService.connectSocket(userType)) {
                is BaseResult.Error -> {
                    setState {
                        HomeContract.State(
                            isLoading = false,
                            error = result.exception.message
                        )
                    }
                }
                is BaseResult.Success -> {
                    setEffect {
                        if (userType is UserType.HostType) {
                            HomeContract.Effect.NavigateHost
                        } else HomeContract.Effect.NavigatePlayer
                    }
                    setState { HomeContract.State(isLoading = false) }
                }
            }
        }
    }

}

