package com.project.saputipuapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.saputipuapp.data.local.User
import com.project.saputipuapp.data.local.UserPreference
import com.project.saputipuapp.data.response.ReportDetailResponse
import com.project.saputipuapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val pref: UserPreference) : ViewModel() {

    val report = MutableLiveData<ReportDetailResponse>()

    fun setReportDetail(user: User, id: String) {
        ApiConfig.getApiService().getReport("Bearer ${user.token}", id)
            .enqueue(object : Callback<ReportDetailResponse> {
                override fun onResponse(
                    call: Call<ReportDetailResponse>,
                    response: Response<ReportDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        report.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ReportDetailResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }


    fun getUser(): LiveData<User> {
        return pref.getUser().asLiveData()
    }

    fun getReportDetail(): LiveData<ReportDetailResponse> {
        return report
    }

    companion object {
        private const val TAG = "ReportTabViewModel"
        private const val SUCCESS = "success"
    }
}