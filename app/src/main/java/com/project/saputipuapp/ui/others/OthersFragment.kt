package com.project.saputipuapp.ui.others

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.project.saputipuapp.R
import com.project.saputipuapp.data.local.UserPreference
import com.project.saputipuapp.databinding.FragmentOthersBinding
import com.project.saputipuapp.ui.ViewModelFactory
import com.project.saputipuapp.ui.login.LoginActivity

class OthersFragment : Fragment() {

    private lateinit var binding: FragmentOthersBinding
    private lateinit var viewModel: OthersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOthersBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(dataStore = requireContext().dataStore)))[OthersViewModel::class.java]

        logout()

        return binding.root
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireActivity(), getString(R.string.logout_success), Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
    }
}