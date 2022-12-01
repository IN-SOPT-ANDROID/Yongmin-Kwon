package org.sopt.sample.data.remote.entity.auth

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInDto(
    val email: String,
    val password: String
)
