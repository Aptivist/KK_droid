package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface WaitingRoomAdminRepository {
    suspend fun sendRequest(eventRequestDomain: EventRequestDomain)
    fun receivePlayers() : Flow<BaseResult<PlayersResponse>>
}