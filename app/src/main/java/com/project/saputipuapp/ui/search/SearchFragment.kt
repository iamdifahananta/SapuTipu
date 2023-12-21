package com.project.saputipuapp.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.saputipuapp.R
import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.databinding.FragmentSearchBinding
import com.project.saputipuapp.ui.detail.DetailActivity

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SearchViewModel::class.java]
        adapter = SearchAdapter()

        binding.rvItem.layoutManager = LinearLayoutManager(activity)
        binding.rvItem.setHasFixedSize(true)
        binding.rvItem.adapter = adapter

        getAccount()
        searchAccount()
        detailReport()

        return binding.root
    }

    private fun getAccount() {
        viewModel.getListReports().observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.isEmpty()) {
                    Toast.makeText(requireContext(), R.string.search_failed, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                } else {
                    adapter.setList(it)
                    showLoading(false)
                }
            }
        }
        binding.search.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchAccount()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun searchAccount() {
        binding.btnSearch.setOnClickListener {
            val id = binding.search.text.toString()

            viewModel.setSearchUser(id)
            showLoading(true)
        }
    }

    private fun detailReport() {
        adapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LaporanItem) {
                Intent(requireActivity(), DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_ID, data.id)
                    startActivity(it)
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}