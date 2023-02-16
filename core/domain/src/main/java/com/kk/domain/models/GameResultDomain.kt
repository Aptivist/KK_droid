package com.kk.domain.models

data class GameResultResponse(
    val status: String,
    val data: GameResultDomain
)
data class GameResultDomain(
    val listPlayers: List<PlayerUserDomain>? = emptyList(),
    val roundPlayerWon: PlayerUserDomain? = null
)
