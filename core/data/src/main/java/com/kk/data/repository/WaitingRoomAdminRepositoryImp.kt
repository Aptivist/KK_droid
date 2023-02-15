package com.kk.data.repository

import com.kk.data.utils.extensions.mapResponse
import com.kk.domain.models.BaseResponseDomain
import com.google.gson.Gson
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.domain.models.PlayerUserDomain
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class WaitingRoomAdminRepositoryImp(
    private val socketService: ISocketService,
    private val gson: Gson
) : WaitingRoomAdminRepository {

    override fun receivePlayers(): Flow<BaseResult<BaseResponseDomain<List<PlayerUserDomain>>>> {
        return try {
            socketService.receiveData().mapResponse(gson)
        } catch (ex: Exception) {
            flow { emit(BaseResult.Error(ex)) }
        }
    }

}