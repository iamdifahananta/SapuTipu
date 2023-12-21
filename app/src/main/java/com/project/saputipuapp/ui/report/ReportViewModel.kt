package com.project.saputipuapp.ui.report

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.saputipuapp.data.local.User
import com.project.saputipuapp.data.local.UserPreference
import com.project.saputipuapp.data.response.ReportResponse
import com.project.saputipuapp.data.retrofit.ApiConfig
import com.project.saputipuapp.utils.ApiCallbackString
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportViewModel(private val pref: UserPreference) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    fun uploadReport(
        user: User,
        accountNumber: String,
        title: String,
        description: String,
        evidence: String,
        callback: ApiCallbackString
    ) {
        ApiConfig.getApiService().addReport("Bearer ${user.token}" ,accountNumber, title, description, evidence)
            .enqueue(object : Callback<ReportResponse> {
                override fun onResponse(
                    call: Call<ReportResponse>,
                    response: Response<ReportResponse>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            callback.onResponse(true, SUCCESS)
                        } else {
                            callback.onResponse(false, "Response body is null")
                        }
                    } else {
                        val jsonObject = JSONTokener(response.errorBody()!!.string()).nextValue() as JSONObject
                        val message = jsonObject.getString("message")
                        callback.onResponse(false, message)
                    }
                }

                override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                    isLoading.value = false
                    Log.e(TAG, "onFailure: ${t.message}")
                    callback.onResponse(false, t.message.toString())
                }

            })
    }

    fun getUser(): LiveData<User> {
        return pref.getUser().asLiveData()
    }

    companion object {
        private const val TAG = "ReportTabViewModel"
        private const val SUCCESS = "success"
    }
}