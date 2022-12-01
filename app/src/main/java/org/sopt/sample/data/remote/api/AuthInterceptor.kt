package org.sopt.sample.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // chain: OkHttp Core 모듈에서 넘어온 chain 객체를 통해서
        // 요청 전 ~ 응답 후의 값을 받아올 수 있음
        // chain.request는 사용자가 보낸 요청값을 가로채는 것
        val originRequest = chain.request()
        // 서버에서 발급 받은 토큰을 응답값에 저장하는 로직이 있어야 함
        // 그래야 SharedPreference에서 토큰 값을 가져와서 여기에다 넣어줘야지
        // newBuilder는 기존 request 기반으로 새로운 parameter를 추가해서 새로운 request를 만들어내는 것이다
        val headerRequest = originRequest.newBuilder()
            .header("token", "")
            .build()
        // proceed를 통해서 서버통신 응답값을 받아올 수 있고 우리는 응답값에 어떠한 커스텀도 안하기에
        // 일단 내려보낸다
        return chain.proceed(headerRequest)
    }
}
