package com.project.saputipuapp.data.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("data")
	val data: Data
)

data class Data(

	@field:SerializedName("neutral")
	val neutral: Float,

	@field:SerializedName("scam")
	val scam: Float,

	@field:SerializedName("spam")
	val spam: Float
)
