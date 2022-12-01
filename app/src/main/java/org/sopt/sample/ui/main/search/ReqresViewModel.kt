package org.sopt.sample.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sample.data.remote.api.ServicePool
import org.sopt.sample.data.remote.entity.reqres.ResponseReqresDTO
import retrofit2.HttpException
import retrofit2.await

class ReqresViewModel : ViewModel() {

    private val _reqresResult = MutableLiveData<List<ResponseReqresDTO.Data>>()
    val reqresResult: LiveData<List<ResponseReqresDTO.Data>> get() = _reqresResult

    fun getReqresInfo() {
        val reqresService = ServicePool.reqresService
        viewModelScope.launch {
            kotlin.runCatching {
                reqresService.getUsers(1).await()
            }.onSuccess {
                _reqresResult.value = it.data
            }.onFailure {
                if (it is HttpException) {
                    Log.e("ReqresFragment", "서버 통신 onResponse but not successful")
                } else {
                    Log.e("ReqresFragment", "서버 통신 onFailure")
                }
            }
        }
    }
}
