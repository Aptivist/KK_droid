package com.kk.data.repository

import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.KKTimerDomain
import kotlinx.coroutines.flow.Flow

interface AnswerRepository {

    suspend fun sendAnswer()

    fun receiveData() : Flow<BaseResult<BaseResponseDomain<KKTimerDomain>>>

}