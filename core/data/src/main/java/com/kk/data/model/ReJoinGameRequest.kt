package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReJoinGameRequest(
    val event: String,
    val code: String,
)
