package org.sopt.sample.domain.model

data class ResponseSignIn(
    val id: Int,
    val name: String,
    val profileImage: String?,
    val bio: String?,
    val email: String,
    val password: String
)
