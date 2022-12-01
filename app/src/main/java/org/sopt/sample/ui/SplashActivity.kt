package org.sopt.sample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.local.MySharedPreferences
import org.sopt.sample.databinding.ActivitySplashBinding
import org.sopt.sample.ui.auth.SignInActivity
import org.sopt.sample.ui.main.HomeActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setName()
        setContentView(binding.root)

        setDelay()
    }

    private fun setName() {
        val name = MySharedPreferences.name ?: return
        binding.textName.text = name
    }

    private fun checkAutoLogin() {
        val id = MySharedPreferences.id
        val autoLogin = MySharedPreferences.autoLogin
        if (id == null) startLogin()
        else if (autoLogin) startHome()
        else startLogin()
    }

    private fun startLogin() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            val fadeAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
            binding.imageView.startAnimation(fadeAnim)
            checkAutoLogin()
            finish()
        }, 2000L)
    }
}
