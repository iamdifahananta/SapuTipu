package com.project.saputipuapp.ui.search.searchTabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.databinding.FragmentSearchTabsBinding
import com.project.saputipuapp.utils.ApiCallbackString

class SearchTabFragment : Fragment() {

    private lateinit var binding: FragmentSearchTabsBinding
    private lateinit var viewModel: SearchTabViewModel
    private lateinit var adapter: SearchTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchTabsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SearchTabViewModel::class.java]
        adapter = SearchTabAdapter()

        setAccountAdapter()
        binding.apply {
            rvItem.layoutManager = LinearLayoutManager(context)
            rvItem.setHasFixedSize(true)
            rvItem.adapter = adapter

            btnSearch.setOnClickListener {
                searchAccount()
            }

            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchAccount()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        return binding.root
    }

    private fun setAccountAdapter() {

        adapter.setOnItemClickCallback(object : SearchTabAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LaporanItem) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun searchAccount() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            viewModel.setSearchAccount(query, object : ApiCallbackString {
                override fun onResponse(success: Boolean, message: String) {
                    if (success) {
                        viewModel.getSearchAccount()
                    } else {
                        Toast.makeText(context, "Data not found", Toast.LENGTH_LONG).show()
                    }
                }

            })
        }
    }
}