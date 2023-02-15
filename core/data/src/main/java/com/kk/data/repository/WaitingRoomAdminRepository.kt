package com.kk.data.repository

import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.domain.models.PlayerUserDomain
import kotlinx.coroutines.flow.Flow

interface WaitingRoomAdminRepository {
    fun receivePlayers() : Flow<BaseResult<BaseResponseDomain<List<PlayerUserDomain>>>>
}