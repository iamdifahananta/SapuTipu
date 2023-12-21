package com.project.saputipuapp.ui.scan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.saputipuapp.data.response.ScanResponse
import com.project.saputipuapp.databinding.FragmentScanBinding

class ScanFragment : Fragment() {

    private lateinit var binding: FragmentScanBinding
    private lateinit var viewModel: ScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScanBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ScanViewModel::class.java]

        setButtonScan()
        observeScanResults()

        return binding.root
    }

    private fun setButtonScan() {
        binding.btnScan.setOnClickListener {
            val text = binding.message.text.toString()
            viewModel.scan(text)
            showLoading(true)
        }
    }

    private fun observeScanResults() {
        viewModel.scanResponse.observe(viewLifecycleOwner) { scanResponse ->
            scanResponse?.let {
                showLoading(false)
                displayScanResults(it)
            }
        }
    }

    private fun displayScanResults(scanResponse: ScanResponse) {
        val percentageFormat = "%.2f%%"

        val scamPercentage = scanResponse.data.scam * 100.0
        val spamPercentage = scanResponse.data.spam * 100.0
        val neutralPercentage = scanResponse.data.neutral * 100.0

        binding.resultScam.text = String.format(percentageFormat, scamPercentage)
        binding.resultSpam.text = String.format(percentageFormat, spamPercentage)
        binding.resultNeutral.text = String.format(percentageFormat, neutralPercentage)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearScanResponse() // Clear scanResponse when fragment is destroyed
    }
}