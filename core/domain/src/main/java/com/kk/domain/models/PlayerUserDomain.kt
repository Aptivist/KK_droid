package com.kk.domain.models


data class PlayerUserDomain(
    val id: String,
    val name: String,
    var points: Int? = null,
    override val code: String,
): UserDomain()
