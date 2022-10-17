package org.sopt.sample.ui.main.gallery

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.data.MySharedPreferences
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.defaultSnackbar
import org.sopt.sample.ui.auth.SignInActivity

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }
    private val sharedPref by lazy { MySharedPreferences(requireContext()) }
    private val GITHUB = "github"
    private val INSTAGRAM = "instagram"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initText()
        clickLogout()
        clickGithub()
        clickInstagram()
    }

    private fun initText() {
        binding.textGalleryId.text = sharedPref.loginId
        binding.textGalleryName.text = sharedPref.loginName
        binding.textMbti.text = sharedPref.loginMbti
    }

    private fun clickLogout() {
        binding.buttonLogout.setOnClickListener {
            with(sharedPref) {
                autoLogin = false
                loginName = null
                loginId = null
            }
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(requireContext(), SignInActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun clickLink(category: String) {
        var link = ""
        var title = ""
        when (category) {
            GITHUB -> {
                link = "https://github.com/briandr97"
                title = getString(R.string.github)
            }
            INSTAGRAM -> {
                link = "https://www.instagram.com/k_dragonm/"
                title = getString(R.string.instagram)
            }
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        val chooser = Intent.createChooser(intent, title)

        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            binding.root.defaultSnackbar(R.string.noApp)
        }
    }

    private fun clickGithub() {
        binding.linkGithub.setOnClickListener {
            clickLink(GITHUB)
        }
    }

    private fun clickInstagram() {
        binding.linkInstagram.setOnClickListener {
            clickLink(INSTAGRAM)
        }
    }
}