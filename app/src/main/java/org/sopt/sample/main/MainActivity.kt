package org.sopt.sample.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initText()
    }

    private fun initText() {
        val id = intent.getStringExtra("id")
        val mbti = intent.getStringExtra("mbti")
        binding.textMypageName.text = getString(R.string.myPageName, id)
        binding.textMypageMbti.text = getString(R.string.myPageMbti, mbti)
    }
}