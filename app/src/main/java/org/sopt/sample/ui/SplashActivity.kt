package org.sopt.sample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.ActivitySplashBinding
import org.sopt.sample.ui.auth.SignInActivity
import org.sopt.sample.ui.main.HomeActivity
import org.sopt.sample.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPref: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        sharedPref = MySharedPreferences()
        sharedPref.init(this)
        setName()
        setContentView(binding.root)

        setDelay()
    }

    private fun setName() {
        val name = sharedPref.loginName ?: return
        binding.textName.text = name
    }

    private fun checkAutoLogin() {
        val id = sharedPref.loginId
        val autoLogin = sharedPref.autoLogin
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