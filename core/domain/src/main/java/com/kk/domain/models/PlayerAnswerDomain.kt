package com.kk.domain.models

data class PlayerAnswerDomain(
    val answer: String,
    val timeStamp: Int,
    override val code: String,
    val id: String,
): UserDomain()