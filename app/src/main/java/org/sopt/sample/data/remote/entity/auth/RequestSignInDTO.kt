package org.sopt.sample.data.remote.entity.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInDTO(
    val email: String,
    val password: String
)
