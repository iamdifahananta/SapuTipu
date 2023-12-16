package com.project.saputipuapp.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class User(
    val token: String
): Parcelable
