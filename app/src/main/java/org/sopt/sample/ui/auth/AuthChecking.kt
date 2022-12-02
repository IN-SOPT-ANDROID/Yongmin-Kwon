package org.sopt.sample.ui.auth

class AuthChecking {

    fun validateId(id: String): EditTextUiState {
        if (haveNumber(id) and haveAlphabet(id) and checkLength(id, ID_RANGE)) {
            return EditTextUiState.CORRECT
        }
        if (id.isEmpty()) {
            return EditTextUiState.NOT_FOCUSED
        }
        return EditTextUiState.INCORRECT
    }

    fun validatePw(pw: String): EditTextUiState {
        if (haveNumber(pw) and haveAlphabet(pw) and haveSpecialCharacter(pw) and checkLength(pw, PW_RANGE)
        ) {
            return EditTextUiState.CORRECT
        }
        if (pw.isEmpty()) {
            return EditTextUiState.NOT_FOCUSED
        }
        return EditTextUiState.INCORRECT
    }

    private fun haveSpecialCharacter(input: String): Boolean {
        return !input.all { Character.isLetterOrDigit(it) }
    }

    private fun haveNumber(input: String): Boolean {
        return input.any { Character.isDigit(it) }
    }

    private fun haveAlphabet(input: String): Boolean {
        if (input.any { it.toString() in "a".."z" }) {
            return true
        }
        if (input.any { it.toString() in "A".."Z" }) {
            return true
        }
        return false
    }

    private fun checkLength(input: String, range: IntRange): Boolean {
        if (input.length in range) {
            return true
        }
        return false
    }

    fun validateMbti(input: String): EditTextUiState {
        if (input in mbtiList) {
            return EditTextUiState.CORRECT
        }
        if (input.isEmpty()) {
            return EditTextUiState.NOT_FOCUSED
        }
        return EditTextUiState.INCORRECT
    }

    fun validateName(input: String): EditTextUiState {
        if (input.length in 2..4) {
            return EditTextUiState.CORRECT
        }
        if (input.isEmpty()) {
            return EditTextUiState.NOT_FOCUSED
        }
        return EditTextUiState.INCORRECT
    }

    private val mbtiList: Set<String> = setOf(
        "ESTJ", "ESTP", "ESFJ", "ESFP", "ENTJ", "ENTP", "ENFJ", "ENFP",
        "ISTJ", "ISTP", "ISFJ", "ISFP", "INTJ", "INTP", "INFJ", "INFP",
        "CUTE", "SEXY"
    )

    companion object {
        val ID_RANGE = IntRange(6, 10)
        val PW_RANGE = IntRange(6, 12)
    }
}
