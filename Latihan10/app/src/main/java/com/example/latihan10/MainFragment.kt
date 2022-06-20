package com.example.latihan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.latihan10.Constant.KEY_A
import com.example.latihan10.Utils.putPreferenceInt
import com.example.latihan10.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            var A = 0

            btnPlus1.setOnClickListener {
                A = +1
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToBFragment())
                putPreferenceInt(requireContext(), KEY_A, A)
            }
            btnPlus10.setOnClickListener {
                A = +10
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToBFragment())
                putPreferenceInt(requireContext(), KEY_A, A)
            }
        }
    }
}



