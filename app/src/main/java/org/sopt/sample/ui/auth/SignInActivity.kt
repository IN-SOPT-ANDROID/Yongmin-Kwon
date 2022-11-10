package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.entity.auth.RequestSignInDTO
import org.sopt.sample.data.remote.entity.auth.ResponseSignInDTO
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.defaultSnackbar
import org.sopt.sample.shortToast
import org.sopt.sample.ui.main.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>
    private val sharedPref by lazy { MySharedPreferences(this) }

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
            val email = binding.editLoginId.text.toString()
            val pw = binding.editLoginPw.text.toString()
            checkLogin(email, pw)
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

    private fun checkAutoLogin() {
        if (!binding.checkBoxAutoLogin.isChecked) return
        sharedPref.autoLogin = true
    }

    private fun checkLogin(email: String, pw: String) {
        val loginService = ServicePool.authService //ServiceFactory.retrofit.create<AuthService>()
        loginService
            .postSignIn(RequestSignInDTO(email, pw))
            .enqueue(object : Callback<ResponseSignInDTO> {
                override fun onResponse(
                    call: Call<ResponseSignInDTO>,
                    response: Response<ResponseSignInDTO>
                ) {
                    if (response.isSuccessful) {
                        checkAutoLogin()
                        goToHome()
                        shortToast(R.string.succeedSignIn)
                    } else shortToast(R.string.failSignIn)
                }

                override fun onFailure(call: Call<ResponseSignInDTO>, t: Throwable) {
                    shortToast(R.string.failSignIn)
                }
            })
    }

    companion object {
        const val EMAIL = "email"
        const val PW = "pw"
    }
}