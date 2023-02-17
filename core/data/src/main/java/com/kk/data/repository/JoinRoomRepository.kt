package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface JoinRoomRepository {
    suspend fun joinRoom(createGameRequestDomain: JoinRoomDomain)
    fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameRoomDomain>>>
}