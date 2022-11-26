package org.sopt.sample.ui.auth

class AuthChecking {

    fun validateId(id: String) {
    }

    private fun haveNumber(input: String): Boolean {
        if (input.none { Character.isDigit(it) }) {
            return false
        }
        return true
    }

    fun isSignUpEmailValid(email: String): Boolean {
        return if (email.isEmpty()) false
        else android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isSignUpPwValid(pw: String): Boolean = (pw.length in 8..12)

    fun isSignUpNameValid(name: String): Boolean = (name.length in 1..3)

    fun isSignUpMbtiValid(mbti: String): Int {
        if (mbti.length < 4) return SHORT
        else if (mbti in mbtiList) return CORRECT
        else return STRANGE
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
