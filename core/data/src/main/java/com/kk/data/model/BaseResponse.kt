package com.kk.data.model

data class BaseResponse <T>(
    val status: String,
    val data: T
)