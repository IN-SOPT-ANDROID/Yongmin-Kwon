package org.sopt.sample.ui.auth.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.sample.domain.model.SignUpInfo
import org.sopt.sample.domain.repository.AuthRepository
import org.sopt.sample.ui.auth.AuthChecking
import org.sopt.sample.ui.auth.EditTextUiState
import org.sopt.sample.util.addSources
import retrofit2.HttpException

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
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
        addSources(listOf(idValidation, pwValidation, nameValidation, mbtiValidation)) {
            isButtonActive()
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
        viewModelScope.launch {
            kotlin.runCatching {
                authRepository.signUp(signUpInfo)
            }.onSuccess {
                _signUpResult.value = signUpInfo
                authRepository.storeUserInfoInLocal(signUpInfo)
            }.onFailure {
                if (it is HttpException) {
                    Log.e("SignUpViewModel", "서버통신 onResponse but not successful")
                } else {
                    Log.e("SignUpViewModel", "서버통신 onFailure")
                }
            }
        }
    }
}
