package org.sopt.sample.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.ui.auth.SignInActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initText()
        clickLogout()
    }

    private fun initText() {
        val sharedPref = MySharedPreferences()
        sharedPref.init(this)
        binding.textMainId.text = sharedPref.loginId
        binding.textMainName.text = sharedPref.loginName
        binding.textMbti.text = sharedPref.loginMbti
    }

    private fun clickLogout() {
        binding.buttonLogout.setOnClickListener {
            val sharedPref = MySharedPreferences()
            sharedPref.init(this)
            sharedPref.autoLogin = false
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}