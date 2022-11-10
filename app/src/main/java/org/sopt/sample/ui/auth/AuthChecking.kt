package org.sopt.sample.ui.auth

import android.content.Context
import org.sopt.sample.R
import org.sopt.sample.shortToast

class AuthChecking {
    fun isSignUpEmailValid(email: String): Boolean {
        return if (email.isEmpty()) false
        else android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isSignUpPwValid(pw: String): Boolean = (pw.length in 8..12)

    fun isSignUpNameValid(name : String) : Boolean = (name.length in 1..3)

    fun isSignUpMbtiValid(mbti: String): Int {
        if (mbti.length < 4) return SHORT
        else if (mbti in mbtiList) return CORRECT
        else return STRANGE
    }

    fun isSignInValid(
        context: Context, id: String?, pw: String?, inputId: String, inputPw: String
    ): Boolean {
        if (id == null) {
            context.shortToast(R.string.needSignUp)
            return false
        } else if ((inputId != id) and (inputPw != pw)) {
            context.shortToast(R.string.checkIdPw)
            return false
        } else if (inputId != id) {
            context.shortToast(R.string.checkId)
            return false
        } else if (inputPw != pw) {
            context.shortToast(R.string.checkPw)
            return false
        } else {
            context.shortToast(R.string.succeedSignIn)
            return true
        }
    }

    private val mbtiList: List<String> = listOf(
        "ESTJ", "ESTP", "ESFJ", "ESFP", "ENTJ", "ENTP", "ENFJ", "ENFP",
        "ISTJ", "ISTP", "ISFJ", "ISFP", "INTJ", "INTP", "INFJ", "INFP",
        "CUTE", "SEXY"
    )

    companion object {
        const val SHORT = 0
        const val STRANGE = 1
        const val CORRECT = 2
    }
}