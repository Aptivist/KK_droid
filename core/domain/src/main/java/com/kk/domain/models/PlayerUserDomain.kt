package com.kk.domain.models


data class PlayersResponse(
    val status: String,
    val data: List<PlayerUserDomain>
)
data class PlayerUserDomain(
    val id: String,
    val name: String,
    var points: Int? = null,
    override val code: String,
): UserDomain()
