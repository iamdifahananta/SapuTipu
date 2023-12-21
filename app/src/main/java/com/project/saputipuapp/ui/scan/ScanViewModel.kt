package com.project.saputipuapp.ui.scan

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.saputipuapp.data.response.ScanResponse
import com.project.saputipuapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanViewModel: ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()
    val scanResponse = MutableLiveData<ScanResponse>()

    @SuppressLint("NullSafeMutableLiveData")
    fun clearScanResponse() {
        scanResponse.value = null
    }

    fun scan(text: String) {
        ApiConfig.getApiService().scanText(text)
            .enqueue(object :Callback<ScanResponse> {
                override fun onResponse(
                    call: Call<ScanResponse>,
                    response: Response<ScanResponse>
                ) {
                    if (response.isSuccessful) {
                        isLoading.value = false
                        scanResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ScanResponse>, t: Throwable) {
                    Log.d("onFailure", t.message!!)
                }

            })
    }


    companion object {
        private const val TAG = "ScanFragmentViewModel"
        private const val SUCCESS = "success"
    }
}