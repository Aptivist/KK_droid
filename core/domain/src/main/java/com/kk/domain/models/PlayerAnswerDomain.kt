package com.kk.domain.models

data class PlayerAnswerDomain(
    val answer: String,
    val timeStamp: Int,
    override val gameCode: String,
    val playerId: String,
): UserDomain()