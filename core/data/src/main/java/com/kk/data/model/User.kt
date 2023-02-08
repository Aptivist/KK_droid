package com.kk.data.model

import kotlinx.serialization.Serializable

@Serializable
abstract class User{
    abstract val code: String?
}

