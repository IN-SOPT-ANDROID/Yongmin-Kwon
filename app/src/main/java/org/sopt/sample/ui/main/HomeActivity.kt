package org.sopt.sample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.ui.main.gallery.GalleryFragment
import org.sopt.sample.ui.main.home.HomeFragment
import org.sopt.sample.ui.main.search.ReqresFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickAnotherBottomNavi()
    }

    private fun clickAnotherBottomNavi() {
        binding.bottomNaviHome.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.menu_home -> transaction.replace(R.id.fragmentContainerHome, HomeFragment())
                R.id.menu_search -> transaction.replace(R.id.fragmentContainerHome, ReqresFragment())
                R.id.menu_gallery -> transaction.replace(R.id.fragmentContainerHome, GalleryFragment())
            }
            transaction.commit()
            true
        }
    }
}