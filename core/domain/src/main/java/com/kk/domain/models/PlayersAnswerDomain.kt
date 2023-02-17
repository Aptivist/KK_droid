package com.kk.domain.models

data class PlayersAnswerDomain(
    val id: String,
    val answer: String,
    override val code: String,
): UserDomain()