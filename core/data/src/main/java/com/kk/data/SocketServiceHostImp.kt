package com.kk.data

import com.kk.network.di.BaseUrl
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*

class SocketServiceHostImp(private val httpClient: HttpClient): ISocketService{
    private var socket: WebSocketSession? = null

    override suspend fun connectSocket() {
        socket = httpClient.webSocketSession {
            url(BaseUrl.urlHost)
        }
    }

    override suspend fun requestSocket() {
        TODO("Not yet implemented")
    }

    override suspend fun receiveData() {
        TODO("Not yet implemented")
    }

    override suspend fun closeSocket() {
        TODO("Not yet implemented")
    }
}