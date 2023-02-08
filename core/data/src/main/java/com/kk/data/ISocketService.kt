package com.kk.data

interface ISocketService {
    suspend fun connectSocket()

    suspend fun sendEvent()

    suspend fun receiveStates()

    suspend fun closeSocket()
}