package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.sopt.sample.data.local.MySharedPreferences
import org.sopt.sample.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.domain.model.SignUpInfo
import org.sopt.sample.ui.auth.SignInActivity.Companion.EMAIL
import org.sopt.sample.ui.auth.SignInActivity.Companion.PW
import org.sopt.sample.ui.auth.viewmodel.SignUpViewModel
import org.sopt.sample.ui.auth.viewmodel.SignUpViewModelFactory

class SignUpActivity : AppCompatActivity() {
    // private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        initViewModel()
        setContentView(binding.root)
        clickSignUp()
        clickBack()
        observeSignUpResult()
    }

    private fun initViewModel() {
        signUpViewModel = ViewModelProvider(
            this,
            SignUpViewModelFactory(AuthRepositoryImpl())
        )[SignUpViewModel::class.java]
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = this
    }

    private fun clickBack() {
        binding.buttonBack.setOnClickListener { finish() }
    }

    private fun clickSignUp() {
        binding.buttonSignUpSignUp.setOnClickListener {
            val signUpInfo = SignUpInfo(
                binding.editSignUpEmail.text.toString(),
                binding.editSignUpPw.text.toString(),
                binding.editSignUpName.text.toString(),
                binding.editSignUpMbti.text.toString()
            )
            signUpViewModel.signUp(signUpInfo)
        }
    }

    private fun observeSignUpResult() {
        signUpViewModel.signUpResult.observe(this) {
            goBackToSignIn(it)
        }
    }

    private fun goBackToSignIn(signUpInfo: SignUpInfo) {
        setResult(
            RESULT_OK,
            Intent(this, SignInActivity::class.java).apply {
                putExtra(EMAIL, signUpInfo.email)
                putExtra(PW, signUpInfo.pw)
            }
        )
        finish()
    }
}
