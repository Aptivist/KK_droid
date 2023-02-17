package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class WaitingRoomAdminRepositoryImp(
    private val socketService: ISocketService,
    private val gson: Gson
) : WaitingRoomAdminRepository {


    override suspend fun sendRequest(eventRequestDomain: EventRequestDomain) {
        socketService.requestSocket(gson.toJson(eventRequestDomain))
    }

    override fun receivePlayers(): Flow<BaseResult<BaseResponseDomain<List<PlayerUserDomain>>>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it)) }
        } catch (ex: Exception) {
            flow { emit(BaseResult.Error(ex)) }
        }
    }

}