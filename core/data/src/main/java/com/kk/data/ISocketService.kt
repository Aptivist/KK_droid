package com.kk.data

interface ISocketService {
    suspend fun connectSocket()

    suspend fun requestSocket()

    suspend fun receiveData()

    suspend fun closeSocket()
}