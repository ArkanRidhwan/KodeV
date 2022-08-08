package com.example.extend1.ui.admin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.extend1.model.User
import com.example.extend1.utils.Constant.COLL_USER
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class LoginViewModel() : ViewModel() {

    private val database = Firebase.database
    private val collUser = database.getReference(COLL_USER)

    fun loginUserByEmailPassword(email: String, password: String): LiveData<User?> {
        val dataUser = MutableLiveData<User?>()
        var user: User? = null
        collUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val valueUser = i.getValue(User::class.java)
                        if (valueUser?.email == email && valueUser.password == password) {
                            user = valueUser
                        }
                    }
                    dataUser.value = user
                }
            }

            override fun onCancelled(error: DatabaseError) {
                user = null
            }
        })
        return dataUser
    }
}