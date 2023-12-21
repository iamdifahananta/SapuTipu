package com.project.saputipuapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.saputipuapp.data.local.User
import com.project.saputipuapp.data.local.UserPreference

class SplashScreenViewModel(private val pref: UserPreference): ViewModel() {

    fun getUser(): LiveData<User> {
        return pref.getUser().asLiveData()
    }

}