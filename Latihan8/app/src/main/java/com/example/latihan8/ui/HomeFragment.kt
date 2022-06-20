package com.example.latihan8.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihan8.R
import com.example.latihan8.data.local.Data
import com.example.latihan8.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homePagedAdapter: HomePagedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePagedAdapter = HomePagedAdapter(requireActivity() as AppCompatActivity)
        homePagedAdapter.submitList(PagedList<Data.generateDataDummy()>)
        binding.rv1.apply {
            setHasFixedSize(true)
            adapter = homePagedAdapter
        }
    }
}