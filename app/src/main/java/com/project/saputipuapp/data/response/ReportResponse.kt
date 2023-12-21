package com.project.saputipuapp.data.response

import com.google.gson.annotations.SerializedName

data class ReportResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)
