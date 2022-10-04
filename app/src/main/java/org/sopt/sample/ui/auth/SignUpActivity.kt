package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
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
    }

    private fun clickSignUp() {
        binding.buttonSignUpSignUp.setOnClickListener {
            if (!isSignUpValid()) return@setOnClickListener
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("id", binding.editSignUpId.text.toString())
            intent.putExtra("pw", binding.editSignUpPw.text.toString())
            intent.putExtra("name", binding.editSignUpName.text.toString())
            intent.putExtra("mbti", binding.editSignUpMbti.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
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
