package com.example.extend1.ui.main.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.extend1.databinding.FragmentChatBinding
import com.example.extend1.model.Chat
import com.example.extend1.ui.student.admin.AdminViewModel
import com.example.extend1.utils.Constant.ID
import com.example.extend1.utils.Constant.NAME
import com.example.extend1.utils.getInstance
import com.example.extend1.utils.showToast

class ChatFragment : Fragment() {

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var binding: FragmentChatBinding
    private val args: ChatFragmentArgs by navArgs()
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        chatAdapter = ChatAdapter()
        binding.rvChat.adapter = chatAdapter

        binding.apply {
            tvNama.text = args.receiverName
        }

        binding.fabChat.setOnClickListener {
            val message = binding.edtMessage.text.toString()
            if (message.isNotEmpty()) {
                val chat = Chat(
                    receiverId = args.receiverId,
                    receiverName = args.receiverName,
                    senderId = getInstance(requireContext()).getString(ID),
                    senderName = getInstance(requireContext()).getString(NAME),
                    message = message
                )
                chatViewModel.sendMessage(chat).observe(viewLifecycleOwner) {
                    if (it) {
                        binding.edtMessage.text.clear()
                        requireContext().showToast("Pesan Terkirim")
                    } else {
                        requireContext().showToast("Pesan Gagal Terkirim")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadChat()
    }

    private fun loadChat() {
        val senderId = getInstance(requireContext()).getString(ID)
        val receiverId = args.receiverId
        chatViewModel.getMessage(senderId, receiverId).observe(viewLifecycleOwner) { listChat ->
            if (listChat != null) {
                chatAdapter.setListData(listChat)
            }
        }
    }
}