package com.kk.data.repository

import android.util.Log
import com.google.gson.Gson
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ProgressGameRepositoryImp(
    private val socketService: ISocketService,
    private val gson: Gson
): ProgressGameRepository {

    override suspend fun startRound(startRoundRequest: EventRequestDomain) {
        socketService.requestSocket(gson.toJson(startRoundRequest))
    }

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<KKTimerDomain>>> {
        return  try {
            socketService.receiveData().map {
                BaseResult.Success(gson.fromJson(it))
            }
        } catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }

}