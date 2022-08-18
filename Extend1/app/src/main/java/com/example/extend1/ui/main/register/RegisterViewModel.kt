package com.example.extend1.ui.main.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.extend1.model.Admin
import com.example.extend1.model.Company
import com.example.extend1.model.Student
import com.example.extend1.utils.Constant.COLL_ADMIN
import com.example.extend1.utils.Constant.COLL_COMPANY
import com.example.extend1.utils.Constant.COLL_STUDENT
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class RegisterViewModel() : ViewModel() {

    private val database = Firebase.database
    private val collUser = database.getReference(COLL_ADMIN)
    private val collCompany = database.getReference(COLL_COMPANY)
    private val collStudent = database.getReference(COLL_STUDENT)

    fun saveAdmin(data: Admin): LiveData<Boolean> {
        val userId = UUID.randomUUID().toString()
        val status = MutableLiveData<Boolean>()
        val admin = Admin.saveAdmin(
            id = userId,
            email = data.email,
            password = data.password,
            name = data.name
        )
        collUser.child(userId).setValue(admin)
            .addOnCompleteListener {
                status.value = true
            }
            .addOnFailureListener {
                status.value = false
            }
        return status
    }

    fun saveCompany(data: Company): LiveData<Boolean> {
        val adminId = UUID.randomUUID().toString()
        val status = MutableLiveData<Boolean>()
        val company = Company.saveCompany(
            id = adminId,
            nama = data.name,
            npwp = data.npwp,
            email = data.email,
            password = data.password
        )
        collCompany.child(adminId).setValue(company)
            .addOnCompleteListener {
                status.value = true
            }
            .addOnFailureListener {
                status.value = false
            }
        return status
    }

    fun saveStudent(data: Student): LiveData<Boolean> {
        val studentId = UUID.randomUUID().toString()
        val status = MutableLiveData<Boolean>()
        val student = Student.saveStudent(
            id = studentId,
            email = data.email,
            name = data.name,
            password = data.password,
            job = data.job,
            age = data.age,
            image = data.image
        )
        collStudent.child(studentId).setValue(student)
            .addOnCompleteListener {
                status.value = true
            }
            .addOnFailureListener {
                status.value = false
            }
        return status
    }
}