package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.mappers.toJoinRequestDTO
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * If you need to use onEach to save data in local you should infer the type of the Flow like in the next line
 * socketService.receiveData().mapResponse<BaseResponseDomain<GameRoomDomain>>(gson).onEach { saveInLocal(it)  }
 */

class JoinRoomRepositoryImp(private val socketService: ISocketService, private val gson: Gson) :
    JoinRoomRepository {
    override suspend fun joinRoom(createGameRequestDomain: JoinRoomDomain) {
        socketService.requestSocket(gson.toJson(createGameRequestDomain.toJoinRequestDTO()))
    }

    override suspend fun closeSession() {
        socketService.closeSocket()
    }

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<PlayerUserDomain>>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it)) }
        } catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }
}



