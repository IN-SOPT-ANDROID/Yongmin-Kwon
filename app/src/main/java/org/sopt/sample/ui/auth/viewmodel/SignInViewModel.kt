package org.sopt.sample.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.entity.auth.RequestSignInDTO
import org.sopt.sample.data.remote.entity.auth.ResponseSignInDTO
import org.sopt.sample.ui.auth.ServerConnectResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val _signInResult = MutableLiveData<ServerConnectResult>()
    val signInResult: LiveData<ServerConnectResult> get() = _signInResult

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun signIn(email: String, pw: String) {
        val loginService = ServicePool.authService // ServiceFactory.retrofit.create<AuthService>()
        loginService
            .postSignIn(RequestSignInDTO(email, pw))
            .enqueue(object : Callback<ResponseSignInDTO> {
                override fun onResponse(
                    call: Call<ResponseSignInDTO>,
                    response: Response<ResponseSignInDTO>
                ) {
                    if (response.isSuccessful) {
                        _signInResult.value = ServerConnectResult.SUCCESS
                    } else {
                        _signInResult.value = ServerConnectResult.ON_RESPONSE_FAIL
                    }
                }

                override fun onFailure(call: Call<ResponseSignInDTO>, t: Throwable) {
                    _signInResult.value = ServerConnectResult.ON_FAILURE
                }
            })
    }
}
