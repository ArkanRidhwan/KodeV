package com.example.extend1.ui.student.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentListChatAdminBinding
import com.example.extend1.utils.gone
import com.example.extend1.utils.visible


class ListAdminFragment : Fragment() {

    private val adminViewModel: AdminViewModel by viewModels()
    private lateinit var binding: FragmentListChatAdminBinding
    private lateinit var adminAdapter: AdminAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListChatAdminBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adminAdapter = AdminAdapter()
        adminAdapter.onItemClick = {
            val action = ListAdminFragmentDirections.actionListAdminFragmentToChatFragment(it.id, it.name)
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        loadAdmin()
    }

    private fun loadAdmin() {
        binding.rvAdmin.adapter = adminAdapter
        binding.progressCircular.visible()
        binding.rvAdmin.gone()
        adminViewModel.getAdmin().observe(viewLifecycleOwner) {
            if (it?.isNotEmpty() == true) {
                adminAdapter.setListData(it)
                binding.progressCircular.gone()
                binding.rvAdmin.visible()
            } else
                binding.progressCircular.gone()
        }
    }
}