package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.shortSnackbar
import org.sopt.sample.ui.auth.AuthChecking.Companion.CORRECT
import org.sopt.sample.ui.auth.AuthChecking.Companion.SHORT
import org.sopt.sample.ui.auth.AuthChecking.Companion.STRANGE
import org.sopt.sample.ui.auth.SignInActivity.Companion.ID
import org.sopt.sample.ui.auth.SignInActivity.Companion.PW

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val authChecking = AuthChecking()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickSignUp()
        clickBack()
    }

    private fun clickBack() {
        binding.buttonBack.setOnClickListener { finish() }
    }

    private fun clickSignUp() {
        binding.buttonSignUpSignUp.setOnClickListener {
            if (!isSignUpValid()) return@setOnClickListener
            val id = binding.editSignUpId.text.toString()
            val pw = binding.editSignUpPw.text.toString()
            val name = binding.editSignUpName.text.toString()
            val mbti = binding.editSignUpMbti.text.toString()
            storeInfoInLocal(id, pw, name, mbti)
            goBackToSignIn(id, pw)
            finish()
        }
    }

    private fun goBackToSignIn(id: String, pw: String) {
        setResult(RESULT_OK, Intent(this, SignInActivity::class.java).apply {
            putExtra(EMAIL, signUpInfo.email)
            putExtra(PW, signUpInfo.pw)
        })
    }

    private fun storeInfoInLocal(id: String, pw: String, name: String, mbti: String) {
        MySharedPreferences(this).run {
            loginId = id
            loginPw = pw
            loginName = name
            loginMbti = mbti
        }
    }

    private fun isSignUpValid(): Boolean {
        val email = binding.editSignUpEmail.text.toString()
        val pw = binding.editSignUpPw.text.toString()
        val name = binding.editSignUpName.text.toString()
        val mbti = binding.editSignUpMbti.text.toString()

        if (!authChecking.isSignUpEmailValid(email)) {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.checkEmail)
            return false
        }
        if (!authChecking.isSignUpPwValid(pw)) {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.checkPw)
            return false
        }
        if (!authChecking.isSignUpNameValid(name)) {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.checkName)
            return false
        }
        when (authChecking.isSignUpMbtiValid(mbti)) {
            CORRECT -> return true
            SHORT -> {
                binding.buttonSignUpSignUp.shortSnackbar(R.string.mbtiIsShort)
                return false
            }
            STRANGE -> {
                binding.buttonSignUpSignUp.shortSnackbar(R.string.mbtiIsStrange)
                return false
            }
        }
        return true
    }
}
