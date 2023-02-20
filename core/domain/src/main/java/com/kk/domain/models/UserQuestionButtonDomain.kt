package com.kk.domain.models

data class ResponseTimerStatusOk(
    val status: String,
    val data: UserQuestionButtonDomain
)

data class UserQuestionButtonDomain(
    val time: Int
)

data class ResponseStatusInitialized(
    val status: String,
    val data: UserQuestionButtonDomain
)

data class DomainStatusInitialized(
    val id: String,
    val name: String,
    val points: Int,
    val code: String,
)