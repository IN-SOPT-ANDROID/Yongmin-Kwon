package org.sopt.sample.ui.auth.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.entity.auth.RequestSignUpDTO
import org.sopt.sample.data.remote.entity.auth.ResponseSignUpDTO
import org.sopt.sample.ui.auth.AuthChecking
import org.sopt.sample.ui.auth.EditTextUiState
import org.sopt.sample.ui.auth.SignUpInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authChecking = AuthChecking()

    private val _signUpResult = MutableLiveData<SignUpInfo>()
    val signUpResult: LiveData<SignUpInfo> get() = _signUpResult

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val mbti = MutableLiveData<String>()

    val idValidation: LiveData<EditTextUiState> = Transformations.map(id) {
        authChecking.validateId(it)
    }
    val pwValidation: LiveData<EditTextUiState> = Transformations.map(pw) {
        authChecking.validatePw(it)
    }
    val nameValidation: LiveData<EditTextUiState> = Transformations.map(name) {
        authChecking.validateName(it)
    }
    val mbtiValidation: LiveData<EditTextUiState> = Transformations.map(mbti) {
        authChecking.validateMbti(it)
    }

    val buttonValidation = MediatorLiveData<Boolean>().apply {
        addSource(idValidation) {
            this.value = isButtonActive()
        }
        addSource(pwValidation) {
            this.value = isButtonActive()
        }
        addSource(nameValidation) {
            this.value = isButtonActive()
        }
        addSource(mbtiValidation) {
            this.value = isButtonActive()
        }
    }

    private fun isButtonActive(): Boolean {
        return (
            (idValidation.value == EditTextUiState.CORRECT) and
                (pwValidation.value == EditTextUiState.CORRECT) and
                (nameValidation.value == EditTextUiState.CORRECT) and
                (mbtiValidation.value == EditTextUiState.CORRECT)
            )
    }

    fun signUp(signUpInfo: SignUpInfo) {
        val signUpService = ServicePool.authService
        signUpService
            .postSignUp(RequestSignUpDTO(signUpInfo.email, signUpInfo.pw, signUpInfo.name))
            .enqueue(
                object : Callback<ResponseSignUpDTO> {
                    override fun onResponse(
                        call: Call<ResponseSignUpDTO>,
                        response: Response<ResponseSignUpDTO>
                    ) {
                        if (response.isSuccessful) {
                            _signUpResult.value = signUpInfo
                        } else {
                            Log.e("SignUpViewModel", "서버통신 onResponse but not successful")
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUpDTO>, t: Throwable) {
                        Log.e("SignUpViewModel", "서버통신 onFailure")
                    }
                }
            )
    }
}
