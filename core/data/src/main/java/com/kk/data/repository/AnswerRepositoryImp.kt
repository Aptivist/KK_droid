package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.mappers.toAnswerRequestDTO
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.EventAnswerRequestDomain
import com.kk.domain.models.KKTimerDomain
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AnswerRepositoryImp(private val socketService: ISocketService, private val gson: Gson): AnswerRepository {

    override suspend fun sendAnswer(eventAnswerRequestDomain: EventAnswerRequestDomain) {
        socketService.requestSocket(gson.toJson(eventAnswerRequestDomain.toAnswerRequestDTO()))
    }

    override suspend fun closeSession() {
        socketService.closeSocket()
    }

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<KKTimerDomain>>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it)) }

        } catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }
}