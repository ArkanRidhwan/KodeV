package com.example.latihan13.ui.meme

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter4(appCompatActivity: AppCompatActivity) : FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = MemeFragment()
            1 -> fragment = MemeFavoriteFragment()
        }
        return fragment as Fragment
    }

}