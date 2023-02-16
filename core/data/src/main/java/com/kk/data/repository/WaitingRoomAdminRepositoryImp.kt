package com.kk.data.repository

import com.kk.data.utils.extensions.mapResponse
import com.google.gson.Gson
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

    override fun receivePlayers(): Flow<BaseResult<PlayersResponse>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it,PlayersResponse::class.java)) }
        } catch (ex: Exception) {
            flow { emit(BaseResult.Error(ex)) }
        }
    }

}