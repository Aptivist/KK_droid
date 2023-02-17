package com.kk.domain.models

data class AnswerDomain(
    val answer: String,
    val timestamp: Long,
    val gameCode: String,
    val playerId: String
)
