package com.kk.data.repository

import com.kk.domain.models.AnswerDomain
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import java.util.concurrent.Flow

interface UserAnswerRepository {
    suspend fun sendAnswer(requestAnswer : AnswerDomain)

    fun receiveData(): kotlinx.coroutines.flow.Flow<BaseResult<BaseResponseDomain<AnswerDomain>>>
}