package org.sopt.sample.data.remote.api

import org.sopt.sample.data.remote.entity.auth.RequestSignInDto
import org.sopt.sample.data.remote.entity.auth.RequestSignUpDto
import org.sopt.sample.data.remote.entity.auth.ResponseSignInDto
import org.sopt.sample.data.remote.entity.auth.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signin")
    fun postSignIn(
        @Body body: RequestSignInDto
    ): Call<ResponseSignInDto>

    @POST("api/user/signup")
    fun postSignUp(
        @Body body: RequestSignUpDto
    ) : Call<ResponseSignUpDto>
}