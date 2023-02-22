package com.kk.data.mappers

import com.kk.data.model.*
import com.kk.domain.models.*

/*
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



fun GameRoom.toDomain() : GameRoomDomain= GameRoomDomain(
    code = code,
    host = host.toDomainHost(),
    rules = rules.toDomainRules(),
    players = players.map { it.toDomainPlayer() }
)
*/

fun RulesDomain.toRulesDTO() : Rules = Rules(
    this.maxPlayers,
    this.points,
    this.timerSeconds
)

fun  CreateGameRequestDomain.toGameRequestDTO() : CreateGameRequest = CreateGameRequest(
    this.rules.toRulesDTO()
)

fun EventAnswerRequestDomain.toAnswerRequestDTO(): EventAnswerRequest = EventAnswerRequest(
    event = event, answer = answerDomain
)

fun  JoinRoomDomain.toJoinRequestDTO() : JoinGameRequest = JoinGameRequest(
    this.name,
    this.code
)


