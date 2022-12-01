package org.sopt.sample.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object MySharedPreferences {
    private lateinit var loginPreferences: SharedPreferences

    fun init(context: Context) {
        loginPreferences = context.getSharedPreferences("USER_AUTH", Context.MODE_PRIVATE)
    }

    var autoLogin: Boolean
        get() = loginPreferences.getBoolean("AUTO_LOGIN", false)
        set(value) = loginPreferences.edit { putBoolean("AUTO_LOGIN", value).apply() }

    var id: String?
        get() = loginPreferences.getString("LOGIN_EMAIL", null)
        set(value) = loginPreferences.edit { putString("LOGIN_EMAIL", value).apply() }

    var pw: String?
        get() = loginPreferences.getString("LOGIN_PW", null)
        set(value) = loginPreferences.edit { putString("LOGIN_PW", value).apply() }

    var name: String?
        get() = loginPreferences.getString("LOGIN_NAME", null)
        set(value) = loginPreferences.edit { putString("LOGIN_NAME", value).apply() }

    var mbti: String?
        get() = loginPreferences.getString("LOGIN_MBTI", null)
        set(value) = loginPreferences.edit { putString("LOGIN_MBTI", value).apply() }

    fun clear() {
        loginPreferences.edit { clear().apply() }
    }
}
