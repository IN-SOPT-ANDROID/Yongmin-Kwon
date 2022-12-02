package org.sopt.sample.data.remote.entity.reqres

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseReqresDTO(
    val data: List<Data>,
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val support: Support,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int
) {
    @Serializable
    data class Data(
        val avatar: String,
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        val id: Int,
        @SerialName("last_name")
        val lastName: String
    )

    @Serializable
    data class Support(
        val text: String,
        val url: String
    )
}