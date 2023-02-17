package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.mappers.toGameRequestDTO
import com.kk.data.utils.extensions.fromJson
import com.kk.data.utils.extensions.mapResponse
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * If you need to use onEach to save data in local you should infer the type of the Flow like in the next line
 * socketService.receiveData().mapResponse<BaseResponseDomain<GameRoomDomain>>(gson).onEach { saveInLocal(it)  }
 */

class CreateRoomRepositoryImp(private val socketService: ISocketService, private val gson: Gson) :
    CreateRoomRepository {
    override suspend fun createRoom(createGameRequestDomain: CreateGameRequestDomain) {
        socketService.requestSocket(gson.toJson(createGameRequestDomain.toGameRequestDTO()))
    }

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameRoomDomain>>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it)) }

        } catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }
}



