package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Rules(
    val maxPlayers: Int,
    val points: Int,
    val timerSeconds: Int
)