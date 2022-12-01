package org.sopt.sample.data.repositoryimpl

import org.sopt.sample.data.local.MySharedPreferences
import org.sopt.sample.data.remote.api.ServicePool
import org.sopt.sample.data.remote.entity.auth.RequestSignInDto
import org.sopt.sample.data.remote.entity.auth.RequestSignUpDto
import org.sopt.sample.data.remote.entity.auth.ResponseSignInDto
import org.sopt.sample.domain.model.ResponseSignIn
import org.sopt.sample.domain.model.SignUpInfo
import org.sopt.sample.domain.repository.AuthRepository
import retrofit2.await

class AuthRepositoryImpl() : AuthRepository {
    private val service = ServicePool.authService

    override suspend fun signIn(email: String, pw: String): ResponseSignIn {
        val result = service.postSignIn(RequestSignInDto(email, pw)).await().result
        return responseSignInMapper(result)
    }

    private fun responseSignInMapper(result: ResponseSignInDto.User): ResponseSignIn {
        return ResponseSignIn(
            id = result.id,
            name = result.name,
            profileImage = result.profileImage,
            bio = result.bio,
            email = result.email,
            password = result.password
        )
    }

    override suspend fun signUp(signUpInfo: SignUpInfo) {
        service.postSignUp(RequestSignUpDto(signUpInfo.email, signUpInfo.pw, signUpInfo.name))
            .await()
    }

    override suspend fun storeUserInfoInLocal(signUpInfo: SignUpInfo) {
        MySharedPreferences.run {
            name = signUpInfo.name
            mbti = signUpInfo.mbti
        }
    }
}
