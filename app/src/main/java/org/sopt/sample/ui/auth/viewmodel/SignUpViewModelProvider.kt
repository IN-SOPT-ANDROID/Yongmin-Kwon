package org.sopt.sample.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.sample.domain.repository.AuthRepository

class SignUpViewModelFactory(private val authRepository: AuthRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(authRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}
