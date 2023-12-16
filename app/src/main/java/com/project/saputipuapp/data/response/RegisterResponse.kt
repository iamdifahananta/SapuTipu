package com.project.saputipuapp.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)
