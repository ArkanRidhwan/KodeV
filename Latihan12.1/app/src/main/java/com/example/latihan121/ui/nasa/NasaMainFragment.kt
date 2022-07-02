package com.example.latihan121.ui.nasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.latihan121.R
import com.example.latihan121.databinding.FragmentNasaMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class NasaMainFragment : Fragment() {

    private lateinit var binding: FragmentNasaMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNasaMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionPagerAdapter = SectionsPagerAdapter3(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITTLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITTLES = intArrayOf(
            R.string.nasa_page,
            R.string.nasa_favorite
        )
    }
}