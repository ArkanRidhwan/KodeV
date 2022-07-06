package com.example.latihan13.ui.meme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.latihan13.R
import com.example.latihan13.databinding.FragmentMemeMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MemeMainFragment : Fragment() {

    private lateinit var binding: FragmentMemeMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemeMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionPagerAdapter = SectionsPagerAdapter4(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITTLES[position])
        }.attach()
    }

    companion object {
        private val TAB_TITTLES = intArrayOf(
            R.string.meme_page,
            R.string.meme_favorite
        )
    }
}