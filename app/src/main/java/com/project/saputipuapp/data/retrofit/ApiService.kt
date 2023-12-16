package com.project.saputipuapp.data.retrofit

import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.data.response.LoginResponse
import com.project.saputipuapp.data.response.RegisterResponse
import com.project.saputipuapp.data.response.ReportAccResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("user")
    fun register(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String
    ): Call<RegisterResponse>


    @GET("bank-account/{id}")
    fun getSearchAccount(
        @Query("id") query: String
    ): Call<ReportAccResponse>
}