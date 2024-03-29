package com.example.extend1.ui.company.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentHomeCompanyBinding
import com.example.extend1.ui.company.tambahSiswa.StudentAdapter
import com.example.extend1.ui.company.tambahSiswa.StudentViewModel
import com.example.extend1.ui.main.MainActivity
import com.example.extend1.utils.getInstance
import com.example.extend1.utils.gone
import com.example.extend1.utils.visible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeCompanyFragment : Fragment() {

    private val studentViewModel: StudentViewModel by viewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentHomeCompanyBinding
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeCompanyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        auth = Firebase.auth
        studentAdapter = StudentAdapter()

        binding.apply {
            fabAdmin.setOnClickListener {
                findNavController().navigate(HomeCompanyFragmentDirections.actionHomeCompanyFragmentToTambahSiswaFragment())
            }
        }

        binding.textView3.setOnClickListener {
            logout()
        }

        studentAdapter.onItemClick = {
            findNavController().navigate(HomeCompanyFragmentDirections.actionHomeCompanyFragmentToAddDataSiswaFragment())
        }

    }

    override fun onResume() {
        super.onResume()
        loadStudent()
    }

    private fun loadStudent() {
        binding.rvStudent.adapter = studentAdapter
        binding.progressCircular.visible()
        binding.rvStudent.gone()
        studentViewModel.getStudentByCompany("KimiaFarma").observe(viewLifecycleOwner) {
            if (it?.isNotEmpty() == true) {
                studentAdapter.setListData(it)
                binding.progressCircular.gone()
                binding.rvStudent.visible()
            }
            else
                binding.progressCircular.gone()
        }
    }

    private fun logout() {
        auth.signOut()
        getInstance(requireContext()).clearPreferences()
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }
}