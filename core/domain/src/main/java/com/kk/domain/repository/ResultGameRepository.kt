package com.kk.domain.repository

import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import com.kk.domain.models.GameResultDomain
import kotlinx.coroutines.flow.Flow

interface ResultGameRepository {

    fun receiveData(): Flow<BaseResult<BaseResponseDomain<GameResultDomain>>>
}