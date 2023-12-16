package com.project.saputipuapp.ui.search.searchTabs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.data.response.ReportAccResponse
import com.project.saputipuapp.data.retrofit.ApiConfig
import com.project.saputipuapp.utils.ApiCallbackString
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTabViewModel : ViewModel() {

    val listReport = MutableLiveData<ArrayList<LaporanItem>>()

    fun setSearchAccount(query: String, callback: ApiCallbackString) {
        ApiConfig.getApiService().getSearchAccount(query)
            .enqueue(object : Callback<ReportAccResponse> {
                override fun onResponse(
                    call: Call<ReportAccResponse>,
                    response: Response<ReportAccResponse>
                ) {
                    if (response.isSuccessful) {
                        listReport.postValue(response.body()?.laporan)
                        callback.onResponse(response.body() != null, SUCCESS)
                    }
                }

                override fun onFailure(call: Call<ReportAccResponse>, t: Throwable) {
                    Log.e(TAG,"onFailure: ${t.message}")
                }

            })
    }

    fun getSearchAccount(): LiveData<ArrayList<LaporanItem>> {
        return listReport
    }

    companion object {
        private const val TAG = "SearchTabViewModel"
        private const val SUCCESS = "success"
    }
}