package org.sopt.sample.data.remote.entity.auth

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDTO(
    val email: String,
    val password: String,
    val name: String
)
