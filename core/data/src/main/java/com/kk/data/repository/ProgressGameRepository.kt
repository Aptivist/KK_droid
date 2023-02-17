package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface ProgressGameRepository {
    suspend fun startRound(startRoundRequest: EventRequestDomain)
    suspend fun correctAnswer(correctAnswerRequest: AddPointRequestDomain)
    suspend fun noPoints(noPointsAnswer: EventRequestDomain)
    fun receiveAnswers() : Flow<BaseResult<PlayersAnswer>>
}