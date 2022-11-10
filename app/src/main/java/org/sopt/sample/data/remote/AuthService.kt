package org.sopt.sample.data.remote

import org.sopt.sample.data.remote.entity.RequestSignInDTO
import org.sopt.sample.data.remote.entity.RequestSignUpDTO
import org.sopt.sample.data.remote.entity.ResponseSignInDTO
import org.sopt.sample.data.remote.entity.ResponseSignUpDTO
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