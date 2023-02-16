package com.kk.data.repository

import com.google.gson.Gson
import com.kk.domain.models.BaseResult
import com.kk.domain.models.GameResultResponse
import com.kk.domain.repository.ResultGameRepository
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ResultGameRepositoryImp(private val socketService: ISocketService, private val gson: Gson):
    ResultGameRepository {

    override fun receiveData(): Flow<BaseResult<GameResultResponse>> {
        return try {
            socketService.receiveData().map { BaseResult.Success(gson.fromJson(it, GameResultResponse::class.java)) }
        } catch (e: Exception){
            flow { emit(BaseResult.Error(e)) }
        }
    }
}