package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameRequest(
    val rules: Rules,
)
