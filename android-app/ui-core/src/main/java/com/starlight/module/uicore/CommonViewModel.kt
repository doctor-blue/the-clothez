package com.starlight.module.uicore

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rogo.iot.module.core.utils.preference.ThemePreference
import com.starlight.module.uicore.utils.setDarkModeTheme
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor() : ViewModel() {


    fun setDarkMode(isDarkMode: Boolean, context: Context) {
        viewModelScope.launch {
            ThemePreference.setDarkMode(context, isDarkMode)
        }
        setDarkModeTheme(isDarkMode)
    }
}