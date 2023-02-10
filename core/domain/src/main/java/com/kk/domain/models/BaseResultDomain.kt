package com.kk.domain.models

data class BaseResultDomain<T>(
    val status: String,
    val data: T
)
