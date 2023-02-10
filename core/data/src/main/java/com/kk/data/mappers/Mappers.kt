package com.kk.data.mappers

import com.kk.data.model.CreateGameRequest
import com.kk.data.model.HostUser
import com.kk.data.model.KKTimer
import com.kk.data.model.PlayerUser
import com.kk.data.model.Rules
import com.kk.domain.models.CreateGameRequestDomain
import com.kk.domain.models.HostUserDomain
import com.kk.domain.models.KKTimerDomain
import com.kk.domain.models.PlayerUserDomain
import com.kk.domain.models.RulesDomain


fun KKTimer.toDomainTimer() : KKTimerDomain = KKTimerDomain(
    this.time
)

fun PlayerUser.toDomainPlayer() : PlayerUserDomain = PlayerUserDomain(
    this.id,
    this.name,
    this.points,
    this.code
)

fun HostUser.toDomainHost() : HostUserDomain = HostUserDomain(
    this.code
)

fun Rules.toDomainRules() : RulesDomain = RulesDomain(
    this.maxPlayers,
    this.points,
    this.timerSeconds
)

fun CreateGameRequest.toDomainGameRequest() : CreateGameRequestDomain = CreateGameRequestDomain(
    this.host.toDomainHost(),
    this.rules.toDomainRules()
)



