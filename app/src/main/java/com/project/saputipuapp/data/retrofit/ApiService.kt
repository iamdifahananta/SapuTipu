package com.project.saputipuapp.data.retrofit

import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.data.response.LoginResponse
import com.project.saputipuapp.data.response.RegisterResponse
import com.project.saputipuapp.data.response.ReportAccResponse
import com.project.saputipuapp.data.response.ReportDetailResponse
import com.project.saputipuapp.data.response.ReportResponse
import com.project.saputipuapp.data.response.ScanResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
        @Path("id") id: String
    ): Call<ReportAccResponse>

    @FormUrlEncoded
    @POST("report")
    fun addReport(
        @Header("Authorization") token: String,
        @Field("account_number") accountNumber: String,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("evidence") evidence: String
    ): Call<ReportResponse>

    @GET("report/{id}")
    fun getReport(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<ReportDetailResponse>

    @FormUrlEncoded
    @POST("prediction-text")
    fun scanText(
        @Field("text") text: String
    ): Call<ScanResponse>
}