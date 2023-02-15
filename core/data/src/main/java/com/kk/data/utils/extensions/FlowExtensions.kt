package com.kk.data.utils.extensions

import com.google.gson.Gson
import com.kk.domain.models.BaseResponseDomain
import com.kk.domain.models.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <reified T : Any> Flow<String>.mapResponse(gson: Gson): Flow<BaseResult<T>> {
    return this.map {
        val response: BaseResponseDomain<T> = gson.fromJson(it)
        BaseResult.Success(response.data)
    }
}
