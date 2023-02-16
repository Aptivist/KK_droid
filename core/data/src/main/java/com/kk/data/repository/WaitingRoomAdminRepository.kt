package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface WaitingRoomAdminRepository {
    fun receivePlayers() : Flow<BaseResult<PlayersResponse>>
}