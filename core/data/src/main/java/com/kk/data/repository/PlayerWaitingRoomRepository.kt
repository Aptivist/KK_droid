package com.kk.data.repository

import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.PlayerUserDomain
import kotlinx.coroutines.flow.Flow

interface PlayerWaitingRoomRepository {
    fun showPlayers(): Flow<BaseResult<BaseResponseDomain<List<PlayerUserDomain>>>>
}