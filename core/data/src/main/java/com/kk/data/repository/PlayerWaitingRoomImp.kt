package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.utils.extensions.mapResponse
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.PlayerUserDomain
import com.kk.domain.models.PlayersResponse
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PlayerWaitingRoomImp(private val service: ISocketService, private val gson: Gson): PlayerWaitingRoomRepository{
    override fun showPlayers(): Flow<BaseResult<PlayersResponse>> {
        return try {
            service.receiveData().map {
                BaseResult.Success(gson.fromJson(it, PlayersResponse::class.java))
            }
        }catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }
}