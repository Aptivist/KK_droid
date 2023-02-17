package com.kk.local.data


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kk.local.domain.GameData
import com.kk.local.domain.PreferencesRepository
import kotlinx.coroutines.flow.first

private const val PREFERENCES_NAME = "preferences_name"
private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class PreferencesRepositoryImpl(private val context: Context): PreferencesRepository {
    override suspend fun saveCode(code: String): String {
      return try{
          val preferencesKey = stringPreferencesKey(code)
          val preferences = context.dataStore.data.first()
          preferences[preferencesKey] ?: ""
      }catch (e: Exception){
          e.printStackTrace()
          ""
      }
    }

    override suspend fun getCode(code: String, gData: GameData) {
        val preferenceKey = stringPreferencesKey(code)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = gData.code ?: ""
        }
    }

    override suspend fun saveGameCode(gCode: String): String {
        return try{
            val preferencesKey = stringPreferencesKey(gCode)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey] ?: ""
        }catch (e: Exception){
            e.printStackTrace()
            ""
        }
    }

    override suspend fun getGameCode(gCode: String, gData: GameData) {
        val preferenceKey = stringPreferencesKey(gCode)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = gData.gameCode ?: ""
        }
    }

    override suspend fun savePlayerId(playerId: String): String {
        return try{
            val preferencesKey = stringPreferencesKey(playerId)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey] ?: ""
        }catch (e: Exception){
            e.printStackTrace()
            ""
        }
    }

    override suspend fun getPlayerId(playerId: String, gData: GameData) {
        val preferenceKey = stringPreferencesKey(playerId)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = gData.playerId ?: ""
        }
    }
}