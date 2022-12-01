package org.sopt.sample.data.remote.api

import org.sopt.sample.data.remote.entity.auth.RequestSignInDTO
import org.sopt.sample.data.remote.entity.auth.RequestSignUpDTO
import org.sopt.sample.data.remote.entity.auth.ResponseSignInDTO
import org.sopt.sample.data.remote.entity.auth.ResponseSignUpDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signin")
    fun postSignIn(
        @Body body: RequestSignInDTO
    ): Call<ResponseSignInDTO>

    @POST("api/user/signup")
    fun postSignUp(
        @Body body: RequestSignUpDTO
    ) : Call<ResponseSignUpDTO>
}