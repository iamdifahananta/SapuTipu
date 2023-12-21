package com.project.saputipuapp.data.response

import com.google.gson.annotations.SerializedName

data class ReportDetailResponse(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("evidence")
	val evidence: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("nomor_rekening_id")
	val nomorRekeningId: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("status")
	val status: String
)
