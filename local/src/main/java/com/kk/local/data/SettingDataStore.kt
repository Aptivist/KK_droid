package com.kk.local.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kk.local.domain.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingDataStore(private val context: Context): PreferencesRepository {

    companion object{
        private const val gameCode: String = "gameCode"
        private val GAME_CODE = stringPreferencesKey(gameCode)
        private val Context.dataStore: DataStore<Preferences> by
        preferencesDataStore(gameCode)
    }

    val getCode: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[GAME_CODE] ?: ""
    }

    override suspend fun saveCode(code: String){
        context.dataStore.edit { preferences ->
            preferences[GAME_CODE] = code
        }
    }

    override suspend fun getCode() {
        TODO("Not yet implemented")
    }

    override suspend fun saveGameCode(gCode: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getGameCode() {
        TODO("Not yet implemented")
    }

    override suspend fun savePlayerId(playerId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getPlayerId() {
        TODO("Not yet implemented")
    }
}

