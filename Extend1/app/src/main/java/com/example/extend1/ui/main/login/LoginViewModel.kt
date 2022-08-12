package com.example.extend1.ui.main.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.extend1.model.Admin
import com.example.extend1.model.User
import com.example.extend1.utils.Constant.COLL_ADMIN
import com.example.extend1.utils.Constant.COLL_USER
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginViewModel() : ViewModel() {

    private val database = Firebase.database
    private val collUser = database.getReference(COLL_USER)
    private val collAdmin = database.getReference(COLL_ADMIN)

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

    fun loginUserByEmail(email: String): LiveData<User?> {
        val dataUser = MutableLiveData<User?>()
        var user: User? = null
        collUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val valueUser = i.getValue(User::class.java)
                        if (valueUser?.email == email) {
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

    fun loginAdminByEmailPassword(email: String, password: String): LiveData<Admin?> {
        val dataAdmin = MutableLiveData<Admin?>()
        var admin: Admin? = null
        collAdmin.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children) {
                        val valueAdmin = i.getValue(Admin::class.java)
                        if (valueAdmin?.email == email && valueAdmin.password == password){
                            admin = valueAdmin
                        }
                    }
                    dataAdmin.value = admin
                }
            }

            override fun onCancelled(error: DatabaseError) {
                admin = null
            }
        })
        return dataAdmin
    }

    fun loginAdminByEmail(email: String): LiveData<Admin?> {
        val dataAdmin = MutableLiveData<Admin?>()
        var admin: Admin? = null
        collAdmin.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children) {
                        val valueAdmin = i.getValue(Admin::class.java)
                        if (valueAdmin?.email == email){
                            admin = valueAdmin
                        }
                    }
                    dataAdmin.value = admin
                }
            }

            override fun onCancelled(error: DatabaseError) {
                admin = null
            }
        })
        return dataAdmin
    }
}