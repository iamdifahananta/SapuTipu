package com.project.saputipuapp.ui.search.searchTabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.saputipuapp.data.response.LaporanItem
import com.project.saputipuapp.databinding.ItemAccReportBinding

class SearchTabAdapter : RecyclerView.Adapter<SearchTabAdapter.AccountViewHolder>() {

    private val list = ArrayList<LaporanItem>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(account: ArrayList<LaporanItem>) {
        list.clear()
        list.addAll(account)
    }

    inner class AccountViewHolder(val binding: ItemAccReportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LaporanItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(item)
            }
            binding.apply {
                tvTitle.text = item.title
                tvDate.text = item.createdAt
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
}