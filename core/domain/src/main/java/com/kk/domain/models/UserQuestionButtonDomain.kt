package com.kk.domain.models

data class UserQuestionButtonResponse(
    val status: String,
    val data: UserQuestionButtonDomain
)

data class UserQuestionButtonDomain(
    val time: Int
)