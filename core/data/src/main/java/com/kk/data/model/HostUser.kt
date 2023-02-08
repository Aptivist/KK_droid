package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HostUser(
    override var code: String? = null
): User()
