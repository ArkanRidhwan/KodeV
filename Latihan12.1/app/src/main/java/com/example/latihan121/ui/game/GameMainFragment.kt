package com.example.latihan121.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.latihan121.R
import com.example.latihan121.databinding.FragmentGameBinding
import com.example.latihan121.databinding.FragmentGameMainBinding
import com.example.latihan121.ui.game.GameMainFragment.Companion.TAB_TITLES
import com.google.android.material.tabs.TabLayoutMediator

class GameMainFragment : Fragment() {

    private lateinit var binding: FragmentGameMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionPagerAdapter = SectionPagerAdapter(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.game_page,
            R.string.game_favorite
        )
    }
}