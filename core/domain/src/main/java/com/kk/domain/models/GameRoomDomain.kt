package com.kk.domain.models


data class GameRoomCodeResponse(
    val status: String,
    val data: GameRoomDomain
)

data class GameRoomDomain(
    var code: String,
    var host: HostUserDomain,
    val rules: RulesDomain,
    val players: List<PlayerUserDomain> = emptyList()
)