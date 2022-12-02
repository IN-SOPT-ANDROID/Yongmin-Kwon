package org.sopt.sample.data.remote

import org.sopt.sample.data.remote.entity.reqres.ResponseReqresDTO
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ReqresService {
    @GET("users")
    fun getUsers(
        @Query("page") page : Int
    ) : Call<ResponseReqresDTO>
}