package com.arash.altafi.englishteachercompose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arash.altafi.englishteachercompose.utils.BaseRepository
import com.arash.altafi.englishteachercompose.utils.EncryptionUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val encryptionUtils: EncryptionUtils
) : BaseRepository() {
    // Token
    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            encryptionUtils.decrypt(preferences[PreferenceKeys.TOKEN] ?: "default_value")
        }
    }
    fun setToken(value: String) = callCache {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.TOKEN] = encryptionUtils.encrypt(value)
        }
    }

    // Name
    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            encryptionUtils.decrypt(preferences[PreferenceKeys.NAME] ?: "default_value")
        }
    }
    fun setName(value: String) = callCache {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.NAME] = encryptionUtils.encrypt(value)
        }
    }

    // Theme
    fun getTheme(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PreferenceKeys.THEME] ?: ""
        }
    }
    fun setTheme(theme: String) = callCache {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.THEME] = theme
        }
    }
    fun changeTheme() = callCache {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.THEME] =
                if (preferences[PreferenceKeys.THEME] == "dark") "light" else "dark"
        }
    }
}

object PreferenceKeys {
    val TOKEN = stringPreferencesKey("user_token")
    val THEME = stringPreferencesKey("app_theme")
    val NAME = stringPreferencesKey("user_name")
}