package com.kk.data.model

import com.kk.domain.models.AnswerDomain

data class EventAnswerRequest(
    val event : String,
    val answer: AnswerDomain
)
