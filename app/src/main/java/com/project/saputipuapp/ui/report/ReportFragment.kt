package com.project.saputipuapp.ui.report

import android.content.Context
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
import com.project.saputipuapp.data.local.User
import com.project.saputipuapp.data.local.UserPreference
import com.project.saputipuapp.databinding.FragmentReportTabsBinding
import com.project.saputipuapp.ui.ViewModelFactory
import com.project.saputipuapp.utils.ApiCallbackString

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportTabsBinding
    private lateinit var user: User
    private lateinit var viewModel: ReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportTabsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(dataStore = requireContext().dataStore)))[ReportViewModel::class.java]

        uploadReport()

        return binding.root
    }

    private fun uploadReport() {
        binding.btnReport.setOnClickListener {
            val accountNumber = binding.accountNumber.text.toString()
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()
            val evidence = binding.evidence.text.toString()
            showLoading(true)

            viewModel.getUser().observe(viewLifecycleOwner) {
                user = User(it.token)

                viewModel.uploadReport(user ,accountNumber, title, description, evidence, object : ApiCallbackString {
                    override fun onResponse(success: Boolean, message: String) {
                        val filterMessage = message.replace(",", " ")
                        val cleanedMessage = filterMessage.filter { it.isLetterOrDigit() || it.isWhitespace() }
                        if (success) {
                            Toast.makeText(requireActivity(), getString(R.string.report_success), Toast.LENGTH_SHORT).show()
                            showLoading(false)
                        } else {
                            Toast.makeText(requireActivity(), getString(R.string.report_failed) + " " + cleanedMessage, Toast.LENGTH_LONG).show()
                            showLoading(false)
                        }
                    }
                })
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
    }
}