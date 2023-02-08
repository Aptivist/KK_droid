package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayerUser(
    val id: String,
    val name: String,
    var points: Int? = null,
    override val code: String,
    ): User()
