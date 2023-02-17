package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface PlayerWaitingRoomRepository {
    fun retrievePlayers(): Flow<BaseResult<PlayersResponse>>

    suspend fun showPlayers(eventRequestDomain: EventRequestDomain)
}