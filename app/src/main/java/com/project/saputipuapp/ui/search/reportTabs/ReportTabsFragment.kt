package com.project.saputipuapp.ui.search.reportTabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.saputipuapp.databinding.FragmentReportTabsBinding

class ReportTabsFragment : Fragment() {

    private lateinit var binding: FragmentReportTabsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportTabsBinding.inflate(inflater, container, false)

        return binding.root
    }


}