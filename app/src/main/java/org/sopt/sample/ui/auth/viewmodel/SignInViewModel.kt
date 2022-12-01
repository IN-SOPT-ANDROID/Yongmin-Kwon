package org.sopt.sample.ui.auth.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.sample.data.remote.api.ServicePool
import org.sopt.sample.data.remote.entity.auth.RequestSignInDTO
import org.sopt.sample.ui.auth.ServerConnectResult
import retrofit2.HttpException
import retrofit2.await

class SignInViewModel : ViewModel() {

    private val _signInResult = MutableLiveData<ServerConnectResult>()
    val signInResult: LiveData<ServerConnectResult> get() = _signInResult

    fun signIn(email: String, pw: String) {
        val loginService = ServicePool.authService
        viewModelScope.launch {
            kotlin.runCatching {
                loginService.postSignIn(RequestSignInDTO(email, pw)).await()
            }.onSuccess {
                _signInResult.value = ServerConnectResult.SUCCESS
            }.onFailure {
                if (it is HttpException) {
                    _signInResult.value = ServerConnectResult.ON_RESPONSE_FAIL
                } else {
                    _signInResult.value = ServerConnectResult.ON_FAILURE
                }
            }
        }
    }
}
