package com.kk.domain.models

data class BaseResponseDomain<T>(
    val status: String,
    val data: T
)
