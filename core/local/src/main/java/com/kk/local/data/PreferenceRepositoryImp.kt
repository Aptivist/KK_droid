package com.kk.local.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kk.local.domain.PreferencesRepository
import com.kk.local.utils.KEY_CODE
import com.kk.local.utils.KEY_PLAYER
import com.kk.local.utils.PREFERENCES_NAME
import kotlinx.coroutines.flow.first


private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class PreferencesRepositoryImpl(private val context: Context) : PreferencesRepository {
    override suspend fun saveGameCode(gCode: String) {
        val preferenceKey = stringPreferencesKey(KEY_CODE)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = gCode

        }
    }

    override suspend fun getGameCode(): String {
        return try {
            val preferencesKey = stringPreferencesKey(KEY_CODE)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey] ?: ""
        } catch (e: Exception){
            e.printStackTrace()
            ""
        }
    }

    override suspend fun savePlayerId(playerId: String) {
        val preferenceKey = stringPreferencesKey(KEY_PLAYER)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = playerId

        }
    }

    override suspend fun getPlayerId(): String {
        return try {
            val preferencesKey = stringPreferencesKey(KEY_PLAYER)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey] ?: ""
        } catch (e: Exception){
            e.printStackTrace()
            ""
        }
    }

    override suspend fun clearPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}