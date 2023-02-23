package com.kk.domain.models

data class GameResultDomain(
    val listPlayers: List<PlayerUserDomain>,
    val roundPlayerWon: PlayerUserDomain? = null
)
