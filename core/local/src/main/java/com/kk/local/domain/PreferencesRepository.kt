package com.kk.local.domain

interface PreferencesRepository {
    suspend fun saveGameCode(gCode: String)
    suspend fun getGameCode() : String
    suspend fun savePlayerId(playerId: String)
    suspend fun getPlayerId(): String
    suspend fun clearPreferences()
    suspend fun saveNumberRound(round: String)
    suspend fun getNumberRound(): String
}