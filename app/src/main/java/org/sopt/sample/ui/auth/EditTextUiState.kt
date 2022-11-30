package org.sopt.sample.ui.auth

enum class EditTextUiState() {
    NOT_FOCUSED,
    CORRECT,
    INCORRECT;

    companion object {
        const val FORM_INVALIDATE = "형식이 맞지 않습니다"
    }
}
