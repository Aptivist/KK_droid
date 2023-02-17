package com.kk.local.domain

interface PreferencesRepository {
    suspend fun saveCode(code: String): String
    suspend fun getCode(code: String, gData: GameData)
    suspend fun saveGameCode(gCode: String): String
    suspend fun getGameCode(gCode: String, gData: GameData)
    suspend fun savePlayerId(playerId: String): String
    suspend fun getPlayerId(playerId: String, gData: GameData)
}