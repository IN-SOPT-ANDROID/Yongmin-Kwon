package org.sopt.sample.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
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
        val name = intent.getStringExtra("name")
        val mbti = intent.getStringExtra("mbti")
        if(name==null){
            val sharedPref = MySharedPreferences()
            sharedPref.init(this)
            binding.textMainName.text = sharedPref.loginName
            binding.textMainId.text = sharedPref.loginId
            binding.textMypageMbti.text = getString(R.string.myPageMbti, sharedPref.loginMbti)
        } else{
            binding.textMainName.text = getString(R.string.myPageName, name)
            binding.textMypageMbti.text = getString(R.string.myPageMbti, mbti)
        }
    }

    private fun clickLogout() {
        binding.buttonLogout.setOnClickListener {
            val sharedPref = MySharedPreferences()
            sharedPref.init(this)
            sharedPref.clear()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}