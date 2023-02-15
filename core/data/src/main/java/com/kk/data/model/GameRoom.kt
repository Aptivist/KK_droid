package com.kk.data.model

data class GameRoom(
    var code: String,
    var host: HostUser,
    val rules: Rules,
    val players: MutableList<PlayerUser> = mutableListOf()
)
