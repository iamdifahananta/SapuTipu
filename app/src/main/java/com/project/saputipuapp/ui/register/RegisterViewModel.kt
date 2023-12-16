package com.project.saputipuapp.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.saputipuapp.data.response.RegisterResponse
import com.project.saputipuapp.data.retrofit.ApiConfig
import com.project.saputipuapp.utils.ApiCallbackString
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    fun register(email: String, name: String, password: String, callback: ApiCallbackString) {
        ApiConfig.getApiService().register(email, name, password)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null)
                            callback.onResponse(response.body() != null, SUCCESS)
                    } else {
                        Log.e(TAG, "onFailure1: ${response.message()}")
                        val jsonObject = JSONTokener(response.errorBody()!!.string()).nextValue() as JSONObject
                        val message = jsonObject.getString("message")
                        callback.onResponse(false, message)
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    isLoading.value = false
                    Log.e(TAG, "OnFailure2: ${t.message}")
                    callback.onResponse(false, t.message.toString())
                }

            })
    }

    companion object {
        private const val TAG = "RegisterActivityViewModel"
        private const val SUCCESS = "success"
    }
}