/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */
package com.nk.we1.utils

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * A helper class that will be used to manage data for [SharedPreferences]
 * Only class that will be used to communicate with [SharedPreferences]
 */
class PreferenceHelper @Inject constructor(private val preferences: SharedPreferences) {
    fun saveBaseUrl(baseUrl: String) {
        preferences.edit().putString(BASE_URL_KEY, baseUrl).apply()
    }
    fun getBaseUrl(): String? {
        return preferences.getString(BASE_URL_KEY, "")
    }
    fun deleteBaseUrl() {
        preferences.edit().remove(BASE_URL_KEY).apply()
    }
    fun savePassword(password: String) {
        preferences.edit().putString(PASSWORD_KEY, password).apply()
    }
    fun getPassword(): String? {
        return preferences.getString(PASSWORD_KEY, "")
    }
    fun deletePassword() {
        preferences.edit().remove(PASSWORD_KEY).apply()
    }
    fun saveAccessToken(accessToken: String) {
        preferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
    }
    fun getAccessToken(): String? {
        return preferences.getString(ACCESS_TOKEN_KEY, "")
    }
    fun deleteAccessToken() {
        preferences.edit().remove(ACCESS_TOKEN_KEY).apply()
    }
    fun saveRefreshToken(refreshToken: String) {
        preferences.edit().putString(REFRESH_TOKEN_KEY, refreshToken).apply()
    }
    fun getRefreshToken(): String? {
        return preferences.getString(REFRESH_TOKEN_KEY, "")
    }
    fun deleteRefreshToken() {
        preferences.edit().remove(REFRESH_TOKEN_KEY).apply()
    }
    fun saveSelectedLanguageCode(selectedLanguageCode: String) {
        preferences.edit().putString(SELECTED_LANGUAGE_KEY, selectedLanguageCode).apply()
    }
    fun getSelectedLanguageCode(): String {
        return preferences.getString(SELECTED_LANGUAGE_KEY, "en") ?: "en"
    }
    fun deleteSelectedLanguageCode() {
        preferences.edit().remove(SELECTED_LANGUAGE_KEY).apply()
    }
    companion object {
        const val BASE_URL_KEY = "base_url"
        const val PASSWORD_KEY = "password"
        const val ACCESS_TOKEN_KEY = "access_token"
        const val REFRESH_TOKEN_KEY = "refresh_token"
        const val SELECTED_LANGUAGE_KEY = "selected_language"
    }
}