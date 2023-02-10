package com.kk.domain.models

data class CreateGameRequestDomain(
    val host: HostUserDomain,
    val rules: RulesDomain,
)
