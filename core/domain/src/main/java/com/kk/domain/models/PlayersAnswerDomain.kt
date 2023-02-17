package com.kk.domain.models

data class PlayersAnswer(
    val status: String,
    val data: List<PlayersAnswerDomain>
)

data class PlayersAnswerDomain(
    val id: String,
    val answer: String,
    override val code: String,
): UserDomain()