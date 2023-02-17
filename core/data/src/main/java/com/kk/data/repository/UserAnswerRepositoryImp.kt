package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.AnswerDomain
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserAnswerRepositoryImp(private val socketService: ISocketService, private val gson: Gson) :
    UserAnswerRepository {
    override suspend fun sendAnswer(requestAnswer: AnswerDomain) {
        socketService.requestSocket(gson.toJson(requestAnswer))
    }

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<AnswerDomain>>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it)) }
        }catch (ex : Exception){
            flow{emit(BaseResult.Error(ex))}
        }
    }
}