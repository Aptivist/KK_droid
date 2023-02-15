package com.kk.domain.models

sealed interface UserType {
    object HostType : UserType
    object PlayerType : UserType
}