package com.example.latihan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.latihan10.Constant.KEY_A
import com.example.latihan10.Utils.getPreferenceInt
import com.example.latihan10.databinding.FragmentBBinding


class BFragment : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var A = getPreferenceInt(requireContext(), KEY_A)
        var B = getPreferenceInt(requireContext(), KEY_A)
        var B = getPreferenceInt(requireContext(), KEY_C)
        var X = A+B+C



        binding.apply {
            textView2.text = X.toString()
        }
    }
}