package org.sopt.sample.domain.repository

import org.sopt.sample.domain.model.ResponseSignIn
import org.sopt.sample.domain.model.SignUpInfo

interface AuthRepository {
    suspend fun signIn(email: String, pw: String): ResponseSignIn
    suspend fun signUp(signUpInfo: SignUpInfo)
    suspend fun storeUserInfoInLocal(signUpInfo: SignUpInfo)
}
