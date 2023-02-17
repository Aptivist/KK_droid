package com.kk.data.repository

import com.kk.domain.models.*
import kotlinx.coroutines.flow.Flow

interface UserQuestionButtonRepository {
    fun receiveData(): Flow<BaseResult<UserQuestionButtonResponse>>
}