package com.kk.data

import com.kk.data.model.BaseResult
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.Flow

interface ISocketService {
    suspend fun connectSocket()

    suspend fun requestSocket(request: String)

    suspend fun receiveData(): Flow<BaseResult<String>>

    suspend fun closeSocket()
}