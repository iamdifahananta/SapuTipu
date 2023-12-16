package com.project.saputipuapp.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.saputipuapp.ui.search.reportTabs.ReportTabsFragment
import com.project.saputipuapp.ui.search.searchTabs.SearchTabFragment

class SectionsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = SearchTabFragment()
            1 -> fragment = ReportTabsFragment()
        }
        return fragment as Fragment
    }


}