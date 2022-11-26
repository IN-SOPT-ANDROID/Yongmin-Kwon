package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.defaultSnackbar
import org.sopt.sample.shortToastString
import org.sopt.sample.ui.auth.viewmodel.SignInViewModel
import org.sopt.sample.ui.main.HomeActivity

class SignInActivity : AppCompatActivity() {
    private val signInViewModel: SignInViewModel by viewModels()
    private lateinit var binding: ActivitySignInBinding
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>
    private val sharedPref by lazy { MySharedPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = signInViewModel
        binding.lifecycleOwner = this

        launchSignUpResult()
        clickSignUp()
        clickLogin()
        observeSignInResult()
    }

    private fun clickSignUp() {
        binding.buttonLoginSignUp.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            signUpLauncher.launch(signUpIntent)
        }
    }

    private fun clickLogin() {
        binding.buttonLoginLogin.setOnClickListener {
            val email = binding.editLoginId.text.toString()
            val pw = binding.editLoginPw.text.toString()
            signInViewModel.signIn(email, pw)
        }
    }

    private fun goToHome() {
        val mainIntent = Intent(this, HomeActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    private fun launchSignUpResult() {
        signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                getResultFromSignUp(it)
            }
    }

    private fun getResultFromSignUp(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            with(binding) {
                root.defaultSnackbar(R.string.succeedSignUp)
                editLoginId.setText(result.data?.getStringExtra(EMAIL))
                editLoginPw.setText(result.data?.getStringExtra(PW))
            }
        } else binding.root.defaultSnackbar(R.string.failSignUp)
    }

    private fun observeSignInResult() {
        signInViewModel.signInResult.observe(this) {
            if (it == ServerConnectResult.SUCCESS) {
                signInSucceed()
            }
            shortToastString(it.message)
        }
    }

    private fun signInSucceed() {
        checkAutoLogin()
        goToHome()
    }

    private fun checkAutoLogin() {
        if (!binding.checkBoxAutoLogin.isChecked) return
        sharedPref.autoLogin = true
    }

    companion object {
        const val EMAIL = "email"
        const val PW = "pw"
    }
}
