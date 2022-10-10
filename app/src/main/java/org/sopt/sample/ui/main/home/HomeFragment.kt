package org.sopt.sample.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }
    private val adapter by lazy { InstaAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        initAdapter()
        setList()
        return binding.root
    }

    private fun initAdapter(){
        binding.recyclerViewHome.adapter = adapter
    }

    private fun setList(){
        val list : List<InstagramData> = listOf(
            InstagramTitle(),
            InstagramContent(name = "권용민", id = "@k_dragonm"),
            InstagramContent(name = "김수빈", id = "@subeenie0608"),
            InstagramContent(name = "최윤정", id = "@c_yun_east"),
            InstagramContent(name = "최유리", id = "@uxri___"),
            InstagramContent(name = "이준원", id = "@junwon1108"),
            InstagramContent(name = "김준우", id = "@???????"),
            InstagramContent(name = "김지영", id = "@zzi.__.0"),
            InstagramContent(name = "김우남", id = "unam_0107"),
            InstagramContent(name = "김세훈", id = "@s2ehun"),
            InstagramContent(name = "이현우", id = "@I2hyunwoo"),
            InstagramContent(name = "강원용", id = "@wony._.k"),
            InstagramContent(name = "최영진", id = "@oznnni"),
            InstagramContent(name = "이영주", id = "@2zerozu")
        )
        adapter.instaList = list
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}