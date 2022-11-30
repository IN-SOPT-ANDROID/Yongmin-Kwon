package org.sopt.sample.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.sopt.sample.databinding.FragmentReqresBinding

class ReqresFragment : Fragment() {
    private var _binding: FragmentReqresBinding? = null
    private val binding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }
    private val reqresViewModel: ReqresViewModel by viewModels()
    private val reqresAdapter: ReqresAdapter by lazy { ReqresAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReqresBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        reqresViewModel.getReqresInfo()
        observeReqresResult()
    }

    private fun observeReqresResult() {
        reqresViewModel.reqresResult.observe(viewLifecycleOwner) {
            finishLoading()
            reqresAdapter.submitList(it)
        }
    }

    private fun initAdapter() {
        binding.recyclerviewReqres.adapter = reqresAdapter
    }

    private fun finishLoading() {
        binding.loadingLottie.visibility = View.GONE
    }
}
