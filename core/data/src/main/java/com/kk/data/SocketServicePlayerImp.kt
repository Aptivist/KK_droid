package com.kk.data

import com.kk.data.model.BaseResult
import com.kk.network.di.BaseUrl
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

class SocketServicePlayerImp(private val httpClient: HttpClient): ISocketService{
    private var socket: WebSocketSession? = null

    override suspend fun connectSocket() {
        socket = httpClient.webSocketSession {
            url(BaseUrl.urlPlayer)
        }
    }

    override suspend fun requestSocket(request: String) {
        socket?.send(request)
    }

    override suspend fun receiveData(): Flow<BaseResult<String>> {
        return try {
            socket?.incoming?.receiveAsFlow()?.map {BaseResult(data = it.data.toString(), status = "")
            } ?: flow {

            }
        }catch (e: Exception){
            flow {

            }
        }
    }

    override suspend fun closeSocket() {
        TODO("Not yet implemented")
    }
}