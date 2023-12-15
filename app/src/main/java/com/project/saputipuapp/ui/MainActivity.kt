package com.project.saputipuapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.saputipuapp.R
import com.project.saputipuapp.databinding.ActivityMainBinding
import com.project.saputipuapp.ui.others.OthersFragment
import com.project.saputipuapp.ui.scan.ScanFragment
import com.project.saputipuapp.ui.search.SearchFragment
import com.project.saputipuapp.ui.tips.TipsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigation

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.botnav_search -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.botnav_scan -> {
                    replaceFragment(ScanFragment())
                    true
                }
                R.id.botnav_tips -> {
                    replaceFragment(TipsFragment())
                    true
                }
                R.id.botnav_others -> {
                    replaceFragment(OthersFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(SearchFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}