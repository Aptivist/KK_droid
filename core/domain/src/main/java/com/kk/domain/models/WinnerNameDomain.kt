package com.kk.domain.models

data class WinnerNameDomain(
    val id: String,
    val name: String,
    val points: Int,
    override val gameCode: String?
): UserDomain()

data class WinnerDomain(
    val listPlayers: List<Any>,
    val roundPlayerWon: roundPlayerWon,
)

data class roundPlayerWon(
    val id: String,
    val name: String,
    val points: Int,
    override val gameCode: String?
): UserDomain()