package com.project.saputipuapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.databinding.ItemAccReportBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.AccountViewHolder>() {

    private val list = ArrayList<LaporanItem>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(account: ArrayList<LaporanItem>) {
        list.clear()
        list.addAll(account)
        notifyDataSetChanged()
    }

    inner class AccountViewHolder(val binding: ItemAccReportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LaporanItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(item)
            }
            binding.apply {
                tvTitle.text = item.title
                val originalDate = item.createdAt
                val parsedDate = parseDateString(originalDate)

                tvDate.text = parsedDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = ItemAccReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: LaporanItem)
    }

    private fun parseDateString(originalDate: String): String {

        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        try {
            val date = originalFormat.parse(originalDate)

            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

            return outputFormat.format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return originalDate
    }
}