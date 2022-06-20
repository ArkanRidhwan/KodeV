package com.example.latihan4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import com.example.latihan4.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sectionsPagerAdapter = SectionsPagerAdapter(this@MainActivity)
//        binding.viewPager.adapter = sectionsPagerAdapter
//        TabLayoutMediator(binding.tabLayout, binding.viewPager){
//            tab, position -> tab.text = resources.getString(TAB_TITTLES[position])
//        }.attach()

    }

    companion object{
        private val TAB_TITTLES = intArrayOf(
            R.string.kartun,
            R.string.nasa
        )
    }
}