package org.sopt.sample.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.defaultSnackbar
import org.sopt.sample.main.MainActivity
import org.sopt.sample.shortToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>
    private var id: String? = null
    private var pw: String? = null
    private var mbti: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launchSignUpResult()
        clickSignUp()
        clickLogin()
    }

    private fun clickSignUp() {
        binding.buttonLoginSignUp.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            signUpLauncher.launch(signUpIntent)
        }
    }

    private fun clickLogin() {
        binding.buttonLoginLogin.setOnClickListener {
            if (!isLoginValid()) return@setOnClickListener
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("id", id)
            mainIntent.putExtra("mbti", mbti)
            startActivity(mainIntent)
        }
    }

    private fun isLoginValid(): Boolean {
        if (id == null) {
            shortToast(R.string.needSignUp)
            return false
        } else if ((binding.editLoginId.text.toString() != id) and (binding.editLoginPw.text.toString() != pw)) {
            shortToast(R.string.checkIdPw)
            return false
        } else if (binding.editLoginId.text.toString() != id) {
            shortToast(R.string.checkId)
            return false
        } else if (binding.editLoginPw.text.toString() != pw) {
            shortToast(R.string.checkPw)
            return false
        } else {
            shortToast(R.string.succeedSignIn)
            return true
        }
    }

    private fun launchSignUpResult() {
        signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                getResultFromSignUp(it)
            }
    }

    private fun getResultFromSignUp(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            id = result.data?.getStringExtra("id")
            pw = result.data?.getStringExtra("pw")
            mbti = result.data?.getStringExtra("mbti")
            binding.root.defaultSnackbar(R.string.succeedSignUp)
        } else binding.root.defaultSnackbar(R.string.failSignUp)
    }
}