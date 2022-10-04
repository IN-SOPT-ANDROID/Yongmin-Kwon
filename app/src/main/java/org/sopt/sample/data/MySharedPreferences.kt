package org.sopt.sample.data

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences {
    private lateinit var loginPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context) {
        loginPreferences = context.getSharedPreferences("USER_AUTH", Context.MODE_PRIVATE)
        editor = loginPreferences.edit()
    }

    var autoLogin: Boolean
        get() = loginPreferences.getBoolean("AUTO_LOGIN", false)
        set(value) = editor.putBoolean("AUTO_LOGIN", value).apply()

    var loginId: String?
        get() = loginPreferences.getString("LOGIN_ID", null)
        set(value) = editor.putString("LOGIN_ID", value).apply()

    var loginPw : String?
        get() = loginPreferences.getString("LOGIN_PW", null)
        set(value) = editor.putString("LOGIN_PW", value).apply()

    var loginName : String?
        get() = loginPreferences.getString("LOGIN_NAME", null)
        set(value) = editor.putString("LOGIN_NAME", value).apply()

    var loginMbti : String?
        get() = loginPreferences.getString("LOGIN_MBTI", null)
        set(value) = editor.putString("LOGIN_MBTI", value).apply()

    fun clear() {
        editor.clear().apply()
    }
}