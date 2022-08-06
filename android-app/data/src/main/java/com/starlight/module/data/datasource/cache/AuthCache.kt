package com.starlight.module.data.datasource.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.starlight.module.data.datasource.entity.TokenEntity
import com.starlight.module.data.datasource.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AuthCache(
    private val context: Context
) {
    private val AUTH_PREFERENCES = "starlight_auth_preferences"
    private val USER_INFO = stringPreferencesKey("user_info")
    private val TOKEN = stringPreferencesKey("token")
    private val Context.userDataStore by preferencesDataStore(
        name = AUTH_PREFERENCES
    )

    fun getUserInfo(
        onError: (Throwable) -> Unit,
    ): Flow<UserEntity?> =
        context.userDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
//            emit(emptyPreferences())
                onError(exception)
                // if get preference error -> return default ThemePreference
            }.map { preferences ->
                // Get our show completed value, defaulting to false if not set:
                Gson().fromJson(preferences[USER_INFO], UserEntity::class.java)
            }

    fun setUserInfo(context: Context, userEntity: UserEntity) {
        CoroutineScope(Dispatchers.Default).launch {
            context.userDataStore.edit { preferences ->
                preferences[USER_INFO] = Gson().toJson(userEntity)
            }
        }
    }

    fun getToken(
        onError: (Throwable) -> Unit,
    ): Flow<TokenEntity?> =
        context.userDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
//            emit(emptyPreferences())
                onError(exception)
                // if get preference error -> return default ThemePreference
            }.map { preferences ->
                // Get our show completed value, defaulting to false if not set:
                Gson().fromJson(preferences[TOKEN], TokenEntity::class.java)
            }


    fun setToken(
        token: TokenEntity?
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            context.userDataStore.edit { preferences ->
                preferences[TOKEN] = if (token != null) Gson().toJson(token) else ""
            }
        }
    }

}