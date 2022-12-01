package org.sopt.sample.data.local

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    private val loginPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        loginPreferences = context.getSharedPreferences("USER_AUTH", Context.MODE_PRIVATE)
        editor = loginPreferences.edit()
    }

    var autoLogin: Boolean
        get() = loginPreferences.getBoolean("AUTO_LOGIN", false)
        set(value) = editor.putBoolean("AUTO_LOGIN", value).apply()

    var loginEmail: String?
        get() = loginPreferences.getString("LOGIN_EMAIL", null)
        set(value) = editor.putString("LOGIN_EMAIL", value).apply()

    var loginPw: String?
        get() = loginPreferences.getString("LOGIN_PW", null)
        set(value) = editor.putString("LOGIN_PW", value).apply()

    var loginName: String?
        get() = loginPreferences.getString("LOGIN_NAME", null)
        set(value) = editor.putString("LOGIN_NAME", value).apply()

    var loginMbti: String?
        get() = loginPreferences.getString("LOGIN_MBTI", null)
        set(value) = editor.putString("LOGIN_MBTI", value).apply()

    fun clear() {
        editor.clear().apply()
    }
}