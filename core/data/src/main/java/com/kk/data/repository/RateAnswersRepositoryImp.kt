package com.kk.data.repository

import android.util.Log
import com.google.gson.Gson
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RateAnswersRepositoryImp(
    private val socketService: ISocketService,
    private val gson: Gson
): RateAnswerRepository {
    override suspend fun addPoint(addPointRequest: AddPointRequestDomain) {
        socketService.requestSocket(gson.toJson(addPointRequest))
    }

    override suspend fun noPoints(noPointsRequest: EventRequestDomain) {
        socketService.requestSocket(gson.toJson(noPointsRequest))
    }

    override suspend fun showAnswers(showAnswersRequest: EventRequestDomain) {
        socketService.requestSocket(gson.toJson(showAnswersRequest))
    }

    override fun receiveAnswers(): Flow<BaseResult<BaseResponseDomain<List<PlayerAnswerDomain>>>> {
        return try {
            socketService.receiveData().map {
                BaseResult.Success(gson.fromJson(it))
            }
        } catch (ex: Exception){
            flow { emit(BaseResult.Error(ex)) }
        }
    }
}