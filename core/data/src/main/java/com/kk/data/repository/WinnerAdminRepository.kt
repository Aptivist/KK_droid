package com.kk.data.repository

import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventRequestDomain
import com.kk.domain.models.WinnerNameDomain
import kotlinx.coroutines.flow.Flow

interface WinnerAdminRepository {
    suspend fun startNextGame(startNextGameRequest: EventRequestDomain)
    fun receiveWinner(): Flow<BaseResult<BaseResponseDomain<WinnerNameDomain>>>
}