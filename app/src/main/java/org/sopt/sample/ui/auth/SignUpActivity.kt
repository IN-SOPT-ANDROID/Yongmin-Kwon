package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.ui.auth.SignInActivity.Companion.EMAIL
import org.sopt.sample.ui.auth.SignInActivity.Companion.PW
import org.sopt.sample.ui.auth.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)
        clickSignUp()
        clickBack()
        observeSignUpResult()
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
            storeInfoInLocal(it)
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

    private fun storeInfoInLocal(signUpInfo: SignUpInfo) {
        MySharedPreferences(this).run {
            loginName = signUpInfo.name
            loginMbti = signUpInfo.mbti
        }
    }
}
