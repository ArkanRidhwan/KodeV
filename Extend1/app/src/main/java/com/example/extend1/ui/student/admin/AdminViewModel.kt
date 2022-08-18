package com.example.extend1.ui.student.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.extend1.model.Admin
import com.example.extend1.utils.Constant.COLL_ADMIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AdminViewModel : ViewModel() {

    private val database = Firebase.database
    private val collAdmin = database.getReference(COLL_ADMIN)

    fun getAdmin(): LiveData<List<Admin>?> {
        val dataAdmin = MutableLiveData<List<Admin>?>()
        val admin = ArrayList<Admin>()
        collAdmin.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    admin.clear()
                    for (i in snapshot.children) {
                        i.getValue(Admin::class.java)?.let {
                            admin.add(it)
                        }
                    }
                    dataAdmin.value = admin
                } else {
                    dataAdmin.value = null
                }
            }

            override fun onCancelled(error: DatabaseError) {
                dataAdmin.value = null
            }
        })
        return dataAdmin
    }
}