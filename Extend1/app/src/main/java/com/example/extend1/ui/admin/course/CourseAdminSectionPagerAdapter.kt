package com.example.extend1.ui.admin.course

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.extend1.ui.admin.home.detail.DetailKegiatanSiswaLaporanFragment
import com.example.extend1.ui.admin.home.detail.DetailKegiatanSiswaLogbookFragment
import com.example.extend1.ui.company.home.LaporanEvaluasiFragment
import com.example.extend1.ui.company.home.LogbookFragment

class CourseAdminSectionPagerAdapter(appCompatActivity: AppCompatActivity) : FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = CourseAdminArtikelFragment()
            1 -> fragment = CourseAdminVideoFragment()
        }
        return fragment as Fragment
    }
}