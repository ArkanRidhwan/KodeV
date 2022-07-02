package com.example.latihan121.ui.nasa

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter3(appCompatActivity: AppCompatActivity) :
    FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when (position) {
            0 -> fragment = NasaFragment()
            1 -> fragment = NasaFavoriteFragment()
        }
        return fragment as Fragment
    }
}