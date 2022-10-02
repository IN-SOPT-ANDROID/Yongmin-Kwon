package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>
    private lateinit var id: String
    private lateinit var pw: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launchSignUpResult()
        clickSignUp()
    }

    private fun clickSignUp() {
        binding.buttonSignUpSignUp.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            signUpLauncher.launch(signUpIntent)
        }
    }

    private fun launchSignUpResult() {
        signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { getResultFromSignUp(it) }
    }

    private fun getResultFromSignUp(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            id = result.data?.getStringExtra("id").toString()
            pw = result.data?.getStringExtra("pw").toString()
        } else {
            binding.buttonSignUpSignUp.shortSnackbar(R.string.failSignUp)
            id = ""
            pw = ""
        }
    }
}