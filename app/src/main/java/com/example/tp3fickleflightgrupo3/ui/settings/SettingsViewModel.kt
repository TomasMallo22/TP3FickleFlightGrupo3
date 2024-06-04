package com.example.tp3fickleflightgrupo3.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    private val _isDarkModeEnabled = MutableLiveData<Boolean>().apply {
        value = sharedPreferences.getBoolean("DARK_MODE", false)
    }
    val isDarkModeEnabled: LiveData<Boolean> get() = _isDarkModeEnabled

    fun toggleDarkMode(enabled: Boolean) {
        _isDarkModeEnabled.value = enabled
        sharedPreferences.edit().putBoolean("DARK_MODE", enabled).apply()
    }
}
