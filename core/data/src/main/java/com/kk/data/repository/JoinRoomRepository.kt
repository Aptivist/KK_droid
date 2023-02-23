package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface JoinRoomRepository {
    suspend fun joinRoom(createGameRequestDomain: JoinRoomDomain)

    suspend fun reJoinRoom(createGameRequestDomain: ReJoinRoomDomain)
    suspend fun closeSession()
    fun receiveData(): Flow<BaseResult<BaseResponseDomain<PlayerUserDomain>>>
}