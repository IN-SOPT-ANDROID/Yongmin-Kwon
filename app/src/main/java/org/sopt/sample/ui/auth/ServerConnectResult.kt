package org.sopt.sample.ui.auth

enum class ServerConnectResult(val message: String) {
    SUCCESS("서버통신이 성공했습니다."),
    ON_RESPONSE_FAIL("서버통신이 애매하게 이상합니다."),
    ON_FAILURE("서버통신이 전혀 안됐습니다.");
}