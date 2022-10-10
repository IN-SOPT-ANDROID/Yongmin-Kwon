package org.sopt.sample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controlBottomNavi()
    }

    private fun controlBottomNavi() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.menu_home -> transaction.replace(R.id.fragmentContainerHome, HomeFragment())
                R.id.menu_search -> transaction.replace(R.id.fragmentContainerHome, SearchFragment())
                R.id.menu_gallery -> transaction.replace(R.id.fragmentContainerHome, GalleryFragment())
                else -> error("바텀 네비게이션 오류! 아이디값 문제!")
            }
            transaction.commit()
            true
        }
    }
}