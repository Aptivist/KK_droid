package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.utils.extensions.fromJson
import com.kk.data.utils.extensions.mapResponse
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PlayerWaitingRoomImp(private val service: ISocketService, private val gson: Gson): PlayerWaitingRoomRepository{
    override fun retrievePlayers(): Flow<BaseResult<BaseResponseDomain<List<PlayerUserDomain>>>> {
        return try {

            service.receiveData().map { BaseResult.Success(gson.fromJson(it)) }

        }catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }

    override suspend fun closeSession() {
        service.closeSocket()
    }

    override suspend fun showPlayers(eventRequestDomain: EventRequestDomain) {
        service.requestSocket(gson.toJson(eventRequestDomain))
    }
}