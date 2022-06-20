package com.example.latihan11.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.latihan11.ui.favorite.FavoriteFragment
import com.example.latihan11.ui.games.GameFragment
import com.example.latihan11.ui.kartun.KartunFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = GameFragment()
            1 -> fragment = FavoriteFragment()
            2 -> fragment = KartunFragment()
        }
        return fragment as Fragment
    }
}