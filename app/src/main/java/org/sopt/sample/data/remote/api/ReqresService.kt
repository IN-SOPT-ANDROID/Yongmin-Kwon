package org.sopt.sample.data.remote.api

import org.sopt.sample.data.remote.entity.reqres.ResponseReqresDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ReqresService {
    @GET("users")
    fun getUsers(
        @Query("page") page : Int
    ) : Call<ResponseReqresDto>
}