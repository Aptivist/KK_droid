package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.mappers.toGameRequestDTO
import com.kk.data.mappers.toJoinRequestDTO
import com.kk.data.utils.extensions.mapResponse
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * If you need to use onEach to save data in local you should infer the type of the Flow like in the next line
 * socketService.receiveData().mapResponse<BaseResponseDomain<GameRoomDomain>>(gson).onEach { saveInLocal(it)  }
 */

class JoinRoomRepositoryImp(private val socketService: ISocketService, private val gson: Gson) :
    JoinRoomRepository {
    override suspend fun joinRoom(createJoinRequestDomain: JoinRoomDomain) {
        socketService.requestSocket(gson.toJson(createJoinRequestDomain.toJoinRequestDTO()))
    }

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameRoomDomain>>> {
        return try {
            socketService.receiveData().mapResponse(gson)
        } catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }
}



