package com.example.extend1.ui.student.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.extend1.R
import com.example.extend1.databinding.FragmentCourseStudentBinding
import com.google.android.material.tabs.TabLayoutMediator


class CourseStudentFragment : Fragment() {

    private lateinit var binding: FragmentCourseStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseStudentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionPagerAdapter =
            CourseStudentPagerAdapter(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.Artikel,
            R.string.Video
        )
    }
}