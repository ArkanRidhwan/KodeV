package com.example.extend1.ui.student.course

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CourseStudentPagerAdapter(appCompatActivity: AppCompatActivity) : FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = CourseStudentArtikelFragment()
            1 -> fragment = CourseStudentVideoFragment()
        }
        return fragment as Fragment
    }
}