package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.utils.extensions.mapResponse
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.GameResultDomain
import com.kk.domain.repository.ResultGameRepository
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ResultGameRepositoryImp(private val socketService: ISocketService, private val gson: Gson): ResultGameRepository {

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameResultDomain>>> {
        return try {
            socketService.receiveData().mapResponse(gson)
        } catch (e: Exception){
            flow { emit(BaseResult.Error(e)) }
        }
    }
}