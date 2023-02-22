package com.kk.domain.models

data class AnswerDomain(
    val answer: String = "",
    val timeStamp: Long,
    val gameCode: String,
    var playerId: String =""
)