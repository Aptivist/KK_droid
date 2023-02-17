package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface CreateRoomRepository {
    suspend fun createRoom(createGameRequestDomain: CreateGameRequestDomain)
    fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameRoomDomain>>>
}