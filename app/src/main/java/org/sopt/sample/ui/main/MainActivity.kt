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
        var id = intent.getStringExtra("id")
        var name = intent.getStringExtra("name")
        var mbti = intent.getStringExtra("mbti")
        if (name == null) {
            val sharedPref = MySharedPreferences()
            sharedPref.init(this)
            id = sharedPref.loginId
            name = sharedPref.loginName
            mbti = sharedPref.loginMbti
        }
        binding.textMainId.text = id
        binding.textMainName.text = name
        binding.textMbti.text = mbti
    }

    private fun clickLogout() {
        binding.buttonLogout.setOnClickListener {
            val sharedPref = MySharedPreferences()
            sharedPref.init(this)
            sharedPref.clear()
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}