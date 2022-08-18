package com.example.extend1.ui.student.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.extend1.R
import com.example.extend1.databinding.FragmentHomeStudentBinding
import com.example.extend1.databinding.FragmentListChatAdminBinding


class HomeStudentFragment : Fragment() {

    private lateinit var binding: FragmentHomeStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeStudentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabStudent.setOnClickListener {
            findNavController().navigate(HomeStudentFragmentDirections.actionHomeStudentFragmentToListAdminFragment())
        }
    }

}