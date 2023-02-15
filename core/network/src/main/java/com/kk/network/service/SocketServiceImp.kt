package com.kk.network.service

import com.kk.network.di.BaseUrl
import com.kk.network.model.NetworkUserTypeResource
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive

class SocketServiceImp(private val httpClient: HttpClient) : ISocketService {
    private var socket: WebSocketSession? = null

    override suspend fun connectSocket(userTypeResource:  NetworkUserTypeResource):Boolean {
            socket = httpClient.webSocketSession {
                url(
                    when (userTypeResource) {
                        NetworkUserTypeResource.HostType -> BaseUrl.urlHost
                        NetworkUserTypeResource.PlayerType -> BaseUrl.urlPlayer
                    }
                )
            }

           return (socket?.isActive == true)
    }

    override suspend fun requestSocket(request: String) {
        socket?.send(request)
    }


    override fun receiveData(): Flow<String> {
        return socket?.incoming?.receiveAsFlow()
                ?.map {
                    (it as Frame.Text).readText()
                } ?: flow { emit("") }
    }

    override suspend fun closeSocket() {
        socket?.close(CloseReason(code = CloseReason.Codes.NORMAL, "SESSION_CLOSED"))
    }
}


