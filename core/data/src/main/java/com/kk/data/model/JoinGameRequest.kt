package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JoinGameRequest(
    val name: String,
    val code: String,
)
