package com.kk.local.domain

interface PreferencesRepository {
    suspend fun saveCode(code: String)
    suspend fun getCode()
    suspend fun saveGameCode(gCode: String)
    suspend fun getGameCode()
    suspend fun savePlayerId(playerId: String)
    suspend fun getPlayerId()
}