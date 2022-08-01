package rogo.iot.module.core.utils.preference

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


object SettingPreference {
    private const val SETTINGS_PREFERENCES = "rogo_setting_preferences"

    val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
    private val IS_WELCOME_SHOWN = booleanPreferencesKey("is_welcome_screen_shown")
    private val Context.isWelComeScreenShownStore by preferencesDataStore(
        name = SETTINGS_PREFERENCES
    )

    fun isWelcomeScreenShown(
        context: Context,
        onError: (Throwable) -> Unit,
        onComplete: (WelcomeScreenPreference) -> Unit
    ) {
        val settingPreferenceFlow: Flow<WelcomeScreenPreference> =
            context.isWelComeScreenShownStore.data
                .catch { exception ->
                    // dataStore.data throws an IOException when an error is encountered when reading data
//            emit(emptyPreferences())
                    onError(exception)
                    // if get preference error -> return default ThemePreference
                    onComplete(WelcomeScreenPreference())
                }.map { preferences ->
                    // Get our show completed value, defaulting to false if not set:
                    val showCompleted = preferences[IS_WELCOME_SHOWN] ?: false
                    WelcomeScreenPreference(showCompleted)
                }
        CoroutineScope(Dispatchers.Main).launch {
            settingPreferenceFlow.collect {
                onComplete(it)
            }
        }
    }

    fun isWelcomeScreenShown(context: Context, shown: Boolean = false) {
        CoroutineScope(Dispatchers.Main).launch {
            context.isWelComeScreenShownStore.edit { preferences ->
                preferences[IS_WELCOME_SHOWN] = shown
            }
        }
    }
}