package com.kk.network.service


import com.kk.network.model.NetworkUserTypeResource
import kotlinx.coroutines.flow.Flow

interface ISocketService {
    suspend fun connectSocket(userTypeResource: NetworkUserTypeResource): Boolean

    suspend fun requestSocket(request: String)

    fun receiveData(): Flow<String>

    suspend fun closeSocket()
}