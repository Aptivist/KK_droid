package com.kk.local.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingDataStore(private val context: Context) {

    companion object{
        private const val gameCode: String = "gameCode"
        private val GAME_CODE = stringPreferencesKey(gameCode)
        private val Context.dataStore: DataStore<Preferences> by
        preferencesDataStore(gameCode)
    }

    val getCode: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[GAME_CODE] ?: ""
    }

    suspend fun saveCode(code: String){
        context.dataStore.edit { preferences ->
            preferences[GAME_CODE] = code
        }
    }
}

