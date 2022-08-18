package com.example.extend1.ui.company.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentAddDataSiswaBinding


class AddDataSiswaFragment : Fragment() {

    private lateinit var binding: FragmentAddDataSiswaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDataSiswaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            ivLaporanAkhir.setOnClickListener {
                findNavController().navigate(AddDataSiswaFragmentDirections.actionAddDataSiswaFragmentToLaporanEvaluasiFragment())
            }

            ivLogbook.setOnClickListener {
                findNavController().navigate(AddDataSiswaFragmentDirections.actionAddDataSiswaFragmentToLogbookFragment())
            }
        }
    }
}