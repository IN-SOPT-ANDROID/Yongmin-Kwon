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
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("pw", pw)
        setResult(RESULT_OK, intent)
    }

    private fun storeInfoInLocal(id: String, pw: String, name: String, mbti: String) {
        val sharedPref = MySharedPreferences()
        sharedPref.init(this)
        sharedPref.loginId = id
        sharedPref.loginPw = pw
        sharedPref.loginName = name
        sharedPref.loginMbti = mbti
    }

    private fun isSignUpValid(): Boolean {
        val id = binding.editSignUpId.text.toString()
        val pw = binding.editSignUpPw.text.toString()
        val name = binding.editSignUpName.text.toString()
        val mbti = binding.editSignUpMbti.text.toString()

        if (!authChecking.isSignUpIdValid(id)) {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.checkId)
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
