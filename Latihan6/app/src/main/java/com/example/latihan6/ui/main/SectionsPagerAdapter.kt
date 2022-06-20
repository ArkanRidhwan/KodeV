package com.example.latihan6.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.latihan6.ui.game.GameFragment
import com.example.latihan6.ui.nasa.NasaFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = GameFragment()
            1 -> fragment = NasaFragment()
        }
        return fragment as Fragment
    }
}