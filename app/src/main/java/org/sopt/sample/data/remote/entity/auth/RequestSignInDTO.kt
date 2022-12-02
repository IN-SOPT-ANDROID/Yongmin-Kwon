package org.sopt.sample.data.remote.entity.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)
