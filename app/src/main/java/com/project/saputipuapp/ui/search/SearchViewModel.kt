package com.project.saputipuapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.data.response.ReportAccResponse
import com.project.saputipuapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    val listReports = MutableLiveData<ArrayList<LaporanItem>>()

    fun setSearchUser(id: String) {
        ApiConfig.getApiService().getSearchAccount(id)
            .enqueue(object : Callback<ReportAccResponse> {
                override fun onResponse(
                    call: Call<ReportAccResponse>,
                    response: Response<ReportAccResponse>
                ) {
                    if (response.isSuccessful) {
                        val reports = response.body()?.laporan
                        if (!reports.isNullOrEmpty()) {
                            listReports.postValue(reports as ArrayList<LaporanItem>)
                        } else {
                            listReports.postValue(ArrayList())  // No data found
                        }
                    }
                }

                override fun onFailure(call: Call<ReportAccResponse>, t: Throwable) {
                    Log.d("onFailure: ", t.message!!)
                }

            })
    }

    fun getListReports(): LiveData<ArrayList<LaporanItem>> {
        return listReports
    }

    companion object {
        private const val TAG = "SearchTabViewModel"
        private const val SUCCESS = "success"
    }
}