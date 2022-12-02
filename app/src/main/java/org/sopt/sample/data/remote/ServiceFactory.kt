package org.sopt.sample.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ServiceFactory {
    private const val AUTH_BASE_URL = "http://3.39.169.52:3000/"
    private const val REQRES_BASE_URL = "https://reqres.in/api/"

    val retrofitAuth: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val retrofitReqres: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    //inline fun <reified T> create(): T = retrofitAuth.create<T>(T::class.java)
}

object ServicePool {
    val authService: AuthService = ServiceFactory.retrofitAuth.create(AuthService::class.java)
    val reqresService : ReqresService = ServiceFactory.retrofitReqres.create(ReqresService::class.java)
}