package com.kk.data

import com.kk.network.di.BaseUrl
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*

interface ISocketConnection {

    suspend fun connectSocket()

}


class SocketSession(private val httpClient: HttpClient): ISocketConnection{

    var socket: WebSocketSession? = null

    override suspend fun connectSocket() {
        socket = httpClient.webSocketSession {
            url(BaseUrl.urlHost)
        }
    }

}