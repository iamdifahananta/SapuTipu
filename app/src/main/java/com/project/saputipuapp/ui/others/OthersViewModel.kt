package com.project.saputipuapp.ui.others

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.saputipuapp.data.local.UserPreference
import kotlinx.coroutines.launch

class OthersViewModel(private val pref: UserPreference) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

}