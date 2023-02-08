package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GameResult(
    val listPlayers: List<PlayerUser>? = emptyList(),
    val roundPlayerWon: PlayerUser? = null
)
