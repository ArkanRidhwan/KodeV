package com.example.extend1.ui.admin.home.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.extend1.ui.admin.course.CourseAdminArtikelFragment
import com.example.extend1.ui.admin.course.CourseAdminVideoFragment

class DetailKegiatanSiswaPagerAdapter(appCompatActivity: AppCompatActivity) :
    FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = DetailKegiatanSiswaLaporanFragment()
            1 -> fragment = DetailKegiatanSiswaLogbookFragment()
        }
        return fragment as Fragment
    }
}