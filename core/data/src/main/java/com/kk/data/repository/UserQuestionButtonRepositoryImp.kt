package com.kk.data.repository

import com.google.gson.Gson
import com.kk.data.utils.extensions.mapResponse
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.KKTimerDomain
import com.kk.domain.models.UserQuestionButtonResponse
import com.kk.network.service.ISocketService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserQuestionButtonRepositoryImp(
    private val socketService: ISocketService,
    private val gson: Gson
) : UserQuestionButtonRepository {

    override fun receiveData(): Flow<BaseResult<UserQuestionButtonResponse>> {
        return try {
            socketService.receiveData().map {
                BaseResult.Success(
                    gson.fromJson(
                        it,
                        UserQuestionButtonResponse::class.java
                    )
                )
            }
        } catch (e: Exception) {
            flow { emit(BaseResult.Error(e)) }
        }
    }
}