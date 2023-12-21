package com.project.saputipuapp.data.response

import com.google.gson.annotations.SerializedName

data class ReportAccResponse(

	@field:SerializedName("laporan")
	val laporan: ArrayList<LaporanItem>?
)

data class LaporanItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)
