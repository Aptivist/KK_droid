package com.kk.data.repository

import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.CreateGameRequestDomain
import com.kk.domain.models.GameRoomDomain
import kotlinx.coroutines.flow.Flow

interface CreateRoomRepository {
    suspend fun createRoom(createGameRequestDomain: CreateGameRequestDomain)
    fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameRoomDomain>>>
}