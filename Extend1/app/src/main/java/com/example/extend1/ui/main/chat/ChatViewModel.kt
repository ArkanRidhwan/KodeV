package com.example.extend1.ui.main.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.extend1.model.Admin
import com.example.extend1.model.Chat
import com.example.extend1.model.Company
import com.example.extend1.model.Student
import com.example.extend1.utils.Constant.COLL_ADMIN
import com.example.extend1.utils.Constant.COLL_CHAT
import com.example.extend1.utils.Constant.COLL_COMPANY
import com.example.extend1.utils.Constant.COLL_STUDENT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class ChatViewModel() : ViewModel() {

    private val database = Firebase.database
    private val collChat = database.getReference(COLL_CHAT)

    fun sendMessage(data: Chat): LiveData<Boolean> {
        val uuid = UUID.randomUUID().toString()
        val status = MutableLiveData<Boolean>()
        val chat = Chat(
            id = uuid,
            receiverId = data.receiverId,
            receiverName = data.receiverName,
            senderId = data.senderId,
            senderName = data.senderName,
            message = data.message
        )
        collChat.child(uuid).setValue(chat)
            .addOnCompleteListener {
                status.value = true
            }
            .addOnFailureListener {
                status.value = false
            }
        return status
    }

    fun getMessage(senderId: String, receiverId: String): LiveData<List<Chat>?> {
        val dataChat = MutableLiveData<List<Chat>?>()
        val chats = ArrayList<Chat>()
        collChat.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    chats.clear()
                    for (i in snapshot.children) {
                        val chat = i.getValue(Chat::class.java)
                        if (
                            (chat?.senderId == senderId && chat.receiverId == receiverId) ||
                            (chat?.receiverId == senderId && chat.senderId == receiverId)
                        ) {
                            chats.add(chat)
                        }
                    }
                    dataChat.value = chats
                }
            }

            override fun onCancelled(error: DatabaseError) {
                chats.clear()
            }
        })
        return dataChat
    }
}