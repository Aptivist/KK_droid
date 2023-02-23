package com.kk.data.repository

import android.util.Log
import com.google.gson.Gson
import com.kk.data.utils.extensions.fromJson
import com.kk.domain.models.*
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WinnerAdminRepositoryImp(
    private val socketService: ISocketService,
    private val gson: Gson
): WinnerAdminRepository {
    override suspend fun startNextGame(startNextGameRequest: EventRequestDomain) {
        socketService.requestSocket(gson.toJson(startNextGameRequest))
    }

    override fun receiveWinner(): Flow<BaseResult<BaseResponseDomain<WinnerDomain>>> {
        return try {
            socketService.receiveData().map {
                Log.e("VERIFYING THE JSON IN WINNER VIEW...",it.toString())
                BaseResult.Success(gson.fromJson(it))
            }
        } catch (ex: java.lang.Exception){
            flow { emit(BaseResult.Error(ex)) }
        }
    }
}