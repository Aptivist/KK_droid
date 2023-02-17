package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.GameResultDomain
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ResultGameRepositoryImp(private val socketService: ISocketService, private val gson: Gson):
    ResultGameRepository {

    override fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameResultDomain>>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it)) }
        } catch (e: Exception){
            flow { emit(BaseResult.Error(e)) }
        }
    }
}