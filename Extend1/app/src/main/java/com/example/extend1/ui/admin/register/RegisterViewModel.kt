package com.example.extend1.ui.admin.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.extend1.model.User
import com.example.extend1.utils.Constant.COLL_USER
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class RegisterViewModel() : ViewModel() {

    private val database = Firebase.database
    private val collUser = database.getReference(COLL_USER)

    fun saveUser(data: User): LiveData<Boolean> {
        val userId = UUID.randomUUID().toString()
        val status = MutableLiveData<Boolean>()
        val user = User.saveUser(
            id = userId,
            email = data.email,
            password = data.password,
            name = data.name
        )
        collUser.child(userId).setValue(user)
            .addOnCompleteListener {
                status.value = true
            }
            .addOnFailureListener {
                status.value = false
            }
        return status
    }
}