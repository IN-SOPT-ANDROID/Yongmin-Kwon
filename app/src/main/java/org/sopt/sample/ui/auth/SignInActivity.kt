package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.defaultSnackbar
import org.sopt.sample.ui.main.MainActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>
    private val authChecking = AuthChecking()
    private var id: String? = null
    private var pw: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
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
            val inputId = binding.editLoginId.text.toString()
            val inputPw = binding.editLoginPw.text.toString()
            if (!authChecking.isSignInValid(this, id, pw, inputId, inputPw))
                return@setOnClickListener
            val mainIntent = Intent(this, MainActivity::class.java)
            checkAutoLogin()
            startActivity(mainIntent)
            finish()
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
            binding.root.defaultSnackbar(R.string.succeedSignUp)
        } else binding.root.defaultSnackbar(R.string.failSignUp)
    }

    private fun checkAutoLogin() {
        if (!binding.checkBoxAutoLogin.isChecked) return
        val sharedPref = MySharedPreferences()
        sharedPref.init(this)
        sharedPref.autoLogin = true
    }
}