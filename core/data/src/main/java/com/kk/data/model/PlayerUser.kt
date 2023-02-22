package com.kk.data.model


data class PlayerUser(
    val id: String,
    val name: String,
    var points: Int? = null,
    override val code: String,
    ): User()
