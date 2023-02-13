package com.kk.data

import com.kk.data.model.BaseResponse
import com.kk.domain.models.BaseResult
import kotlinx.coroutines.flow.Flow

interface ISocketService {
    suspend fun connectSocket(userType: UserType): BaseResult<String>

    suspend fun requestSocket(request: String)

    fun receiveData(): Flow<BaseResult<String>>

    suspend fun closeSocket()
}