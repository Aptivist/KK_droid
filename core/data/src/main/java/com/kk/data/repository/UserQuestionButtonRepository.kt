package com.kk.data.repository

import com.kk.domain.models.BaseResult
import com.kk.domain.models.ResponseTimerStatusOk
import kotlinx.coroutines.flow.Flow

interface UserQuestionButtonRepository {
    fun receiveData(): Flow<BaseResult<ResponseTimerStatusOk>>
}