package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface ProgressGameRepository {
    suspend fun startRound(startRoundRequest: EventRequestDomain)
    fun receiveData() : Flow<BaseResult<BaseResponseDomain<KKTimerDomain>>>
}