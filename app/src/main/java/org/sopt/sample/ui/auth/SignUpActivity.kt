package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.entity.auth.RequestSignUpDTO
import org.sopt.sample.data.remote.entity.auth.ResponseSignUpDTO
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.shortSnackbar
import org.sopt.sample.shortToast
import org.sopt.sample.ui.auth.AuthChecking.Companion.CORRECT
import org.sopt.sample.ui.auth.AuthChecking.Companion.SHORT
import org.sopt.sample.ui.auth.AuthChecking.Companion.STRANGE
import org.sopt.sample.ui.auth.SignInActivity.Companion.EMAIL
import org.sopt.sample.ui.auth.SignInActivity.Companion.PW
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val signUpInfo = SignUpInfo(
                binding.editSignUpEmail.text.toString(),
                binding.editSignUpPw.text.toString(),
                binding.editSignUpName.text.toString(),
                binding.editSignUpMbti.text.toString()
            )
            initSignUpNetwork(signUpInfo)
        }
    }

    private fun initSignUpNetwork(signUpInfo: SignUpInfo) {
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
                            storeInfoInLocal(signUpInfo)
                            goBackToSignIn(signUpInfo)
                        } else shortToast(R.string.serverConnectResponseError)
                    }

                    override fun onFailure(call: Call<ResponseSignUpDTO>, t: Throwable) {
                        shortToast(R.string.serverConnectOnFailure)
                    }
                }
            )
    }

    private fun goBackToSignIn(signUpInfo: SignUpInfo) {
        setResult(RESULT_OK, Intent(this, SignInActivity::class.java).apply {
            putExtra(EMAIL, signUpInfo.email)
            putExtra(PW, signUpInfo.pw)
        })
        finish()
    }

    private fun storeInfoInLocal(signUpInfo: SignUpInfo) {
        MySharedPreferences(this).run {
            loginName = signUpInfo.name
            loginMbti = signUpInfo.mbti
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
