package com.project.saputipuapp.ui.detail

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.project.saputipuapp.R
import com.project.saputipuapp.data.local.User
import com.project.saputipuapp.data.local.UserPreference
import com.project.saputipuapp.databinding.ActivityDetailBinding
import com.project.saputipuapp.ui.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var user: User
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(dataStore)))[DetailViewModel::class.java]
        user = User("default_token")

        showLoading(true)
        getUserData()
        reportDetail()
    }

    private fun getUserData() {
        viewModel.getUser().observe(this) {
            user = User(
                it.token
            )
        }
    }

    private fun reportDetail() {
        val reportId = intent.getStringExtra(EXTRA_ID)
        if (reportId != null) {
            viewModel.setReportDetail(user, reportId)
        } else {
            Log.e(TAG, "ReportId is null")
        }
        viewModel.getReportDetail().observe(this) {
            if (it != null) {
                showLoading(false)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = dateFormat.parse(it.createdAt)
                val formattedDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)

                binding.tvTitle.text = it.title
                binding.tvCreatedAt.text = formattedDate
                binding.description.text = it.body
                binding.evidence.text = it.evidence
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
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
        const val EXTRA_ID = "extra_id"
    }
}