package com.kk.presentation.host.waitingroomadmin

import androidx.lifecycle.viewModelScope
import com.kk.data.repository.WaitingRoomAdminRepository
import com.kk.domain.models.BaseResult
import com.kk.presentation.R
import com.kk.presentation.baseMVI.BaseViewModelNoEvents
import com.kk.presentation.di.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaitingRoomAdminViewModel (private val waitingRoomAdminRepository: WaitingRoomAdminRepository,
                                 private val stringProvider: StringProvider
) : BaseViewModelNoEvents<
        WaitingRoomAdminContract.State,
        WaitingRoomAdminContract.Effect>() {

    init {
        observeData()
    }

    override fun createInitialState(): WaitingRoomAdminContract.State {
        return WaitingRoomAdminContract.State()
    }

    private fun observeData(){
        viewModelScope.launch(Dispatchers.IO){
            waitingRoomAdminRepository.receivePlayers().collect{ result ->
                when (result){
                    is BaseResult.Error -> setState { copy(error = stringProvider.getString(R.string.wr_error_connection)) }
                    is BaseResult.Success -> setEffect { WaitingRoomAdminContract.Effect.Navigate }
                }
            }
        }
    }

}