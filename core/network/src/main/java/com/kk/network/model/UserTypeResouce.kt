package com.kk.network.model

sealed interface NetworkUserTypeResource {
    object HostType : NetworkUserTypeResource
    object PlayerType : NetworkUserTypeResource
}