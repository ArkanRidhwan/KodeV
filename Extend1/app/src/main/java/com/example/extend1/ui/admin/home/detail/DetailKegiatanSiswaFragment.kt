package com.example.extend1.ui.admin.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.extend1.R
import com.example.extend1.databinding.FragmentDetailKegiatanSiswaBinding
import com.google.android.material.tabs.TabLayoutMediator


class DetailKegiatanSiswaFragment : Fragment() {

    private lateinit var binding: FragmentDetailKegiatanSiswaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailKegiatanSiswaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionPagerAdapter =
            DetailKegiatanSiswaPagerAdapter(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.Laporan,
            R.string.Logbook
        )
    }
}