package org.sopt.sample.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.entity.reqres.ResponseReqresDTO
import org.sopt.sample.databinding.FragmentReqresBinding
import org.sopt.sample.shortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReqresFragment : Fragment() {
    private var _binding: FragmentReqresBinding? = null
    private val binding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }

    private val reqresAdapter: ReqresAdapter by lazy { ReqresAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReqresBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initReqresNetwork()
    }

    private fun initAdapter() {
        binding.recyclerviewReqres.adapter = reqresAdapter
    }

    private fun initReqresNetwork() {
        val reqresService = ServicePool.reqresService
        reqresService.getUsers(1).enqueue(
            object : Callback<ResponseReqresDTO> {
                override fun onResponse(
                    call: Call<ResponseReqresDTO>,
                    response: Response<ResponseReqresDTO>
                ) {
                    if (response.isSuccessful) {
                        reqresAdapter.submitList(response.body()?.data)
                    } else requireContext().shortToast(R.string.serverConnectResponseError)
                }

                override fun onFailure(call: Call<ResponseReqresDTO>, t: Throwable) {
                    requireContext().shortToast(R.string.serverConnectOnFailure)
                }

            }
        )
    }
}