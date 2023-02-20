package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface RateAnswerRepository {
    suspend fun addPoint(addPointRequest: AddPointRequestDomain)
    suspend fun noPoints(noPointsRequest: EventRequestDomain)
    fun receiveAnswers(): Flow<BaseResult<BaseResponseDomain<List<PlayerAnswerDomain>>>>
}