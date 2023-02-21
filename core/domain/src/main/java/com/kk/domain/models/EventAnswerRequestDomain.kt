package com.kk.domain.models

data class EventAnswerRequestDomain(
    val event : String = "SEND_ANSWER",
    val answerDomain: AnswerDomain
)
