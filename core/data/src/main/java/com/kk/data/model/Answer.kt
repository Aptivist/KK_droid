package com.kk.data.model

data class Answer(
    val answer: String?,
    val timeStamp: Long,
    val gameCode: String,
    var playerId: String?
)