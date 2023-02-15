package com.kk.data.repository

import com.kk.domain.models.BaseResult
import com.kk.domain.models.UserType
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun connectSession(userType: UserType): BaseResult<String>
}