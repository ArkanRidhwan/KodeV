package com.example.latihan121.ui.kartun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.latihan121.R
import com.example.latihan121.databinding.FragmentKartunBinding
import com.example.latihan121.databinding.FragmentKartunMainBinding
import com.example.latihan121.ui.game.GameMainFragment
import com.example.latihan121.ui.game.SectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class KartunMainFragment : Fragment() {

    private lateinit var binding: FragmentKartunMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKartunMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionPagerAdapter = SectionsPagerAdapter2(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITTLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITTLES = intArrayOf(
            R.string.kartun_page,
            R.string.kartun_favorite
        )
    }
}