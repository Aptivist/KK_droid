package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface WinnerAdminRepository {
    suspend fun startNextGame(startNextGameRequest: EventRequestDomain)
    fun receiveWinner(): Flow<BaseResult<BaseResponseDomain<WinnerDomain>>>
}