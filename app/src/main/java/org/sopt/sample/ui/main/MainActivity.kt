package org.sopt.sample.ui.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.defaultSnackbar
import org.sopt.sample.ui.auth.SignInActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initText()
        clickLogout()
        clickGithub()
        clickInstagram()
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
            sharedPref.loginName = null
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun clickGithub() {
        binding.linkGithub.setOnClickListener {
            val githubIntent: Intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/briandr97"))

            try {
                startActivity(githubIntent)
            } catch (e: ActivityNotFoundException) {
                binding.root.defaultSnackbar(R.string.noApp)
            }
        }
    }

    private fun clickInstagram() {
        binding.linkInstagram.setOnClickListener {
            val instaIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/k_dragonm/"))
            val title = getString(R.string.instagram)
            val chooser = Intent.createChooser(instaIntent, title)

            try {
                startActivity(chooser)
            } catch (e: ActivityNotFoundException) {
                binding.root.defaultSnackbar(R.string.noApp)
            }
        }
    }
}