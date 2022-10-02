package org.sopt.sample.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.shortSnackbar

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val mbtiList: List<String> = listOf(
        "ESTJ", "ESTP", "ESFJ", "ESFP", "ENTJ", "ENTP", "ENFJ", "ENFP",
        "ISTJ", "ISTP", "ISFJ", "ISFP", "INTJ", "INTP", "INFJ", "INFP"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickSignUp()
    }

    private fun clickSignUp() {
        binding.buttonSignUpSignUp.setOnClickListener {
            if (!isSignUpValid()) return@setOnClickListener
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", binding.editSignUpId.text.toString())
            intent.putExtra("pw", binding.editSignUpPw.text.toString())
            intent.putExtra("mbti", binding.editSignUpMbti.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun isSignUpValid(): Boolean {
        if (!isIdValid()) return false
        if (!isPwValid()) return false
        if (!isMbtiValid()) return false
        else return true
    }

    private fun isIdValid(): Boolean {
        val idLength = binding.editSignUpId.length()
        if ((idLength >= 6) and (idLength <= 10)) return true
        else {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.checkId)
            return false
        }
    }

    private fun isPwValid(): Boolean {
        val pwLength = binding.editSignUpPw.length()
        if ((pwLength >= 8) and (pwLength <= 12)) return true
        else {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.checkPw)
            return false
        }
    }

    private fun isMbtiValid(): Boolean {
        val mbti = binding.editSignUpMbti.text.toString()
        if (mbti in mbtiList) return true
        else if (mbti.length < 4) {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.mbtiIsShort)
            return false
        } else {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.mbtiIsStrange)
            return false
        }
    }
}
