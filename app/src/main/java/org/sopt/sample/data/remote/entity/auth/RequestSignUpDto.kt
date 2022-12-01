package org.sopt.sample.data.remote.entity.auth

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    val email: String,
    val password: String,
    val name: String
)
