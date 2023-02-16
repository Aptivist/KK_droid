package com.kk.domain.models

data class GameResultDomain(
    val listPlayers : List<PlayerUserDomain>? = emptyList(),
    val roundPlayerWon: PlayerUserDomain? = null
)
