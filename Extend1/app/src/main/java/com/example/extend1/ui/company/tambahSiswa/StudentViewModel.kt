package com.example.extend1.ui.company.tambahSiswa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.extend1.model.Student
import com.example.extend1.utils.Constant.COLL_STUDENT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class StudentViewModel : ViewModel() {

    private val database = Firebase.database
    private val collStudent = database.getReference(COLL_STUDENT)

    fun saveStudent(data: Student): LiveData<Boolean> {
        val studentId = UUID.randomUUID().toString()
        val status = MutableLiveData<Boolean>()
        val student = Student(
            id = studentId,
            name = data.name,
            job = data.job,
            age = data.age,
            image = data.image,
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

    fun getStudent(): LiveData<List<Student>?> {
        val dataStudent = MutableLiveData<List<Student>?>()
        val student = ArrayList<Student>()
        collStudent.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    student.clear()
                    for (i in snapshot.children) {
                        i.getValue(Student::class.java)?.let {
                            student.add(it)
                        }
                    }
                    dataStudent.value = student
                } else {
                    dataStudent.value = null
                }
            }

            override fun onCancelled(error: DatabaseError) {
                dataStudent.value = null
            }
        })
        return dataStudent
    }

    fun getStudentByCompany(companyName: String): LiveData<List<Student>?> {
        val dataStudent = MutableLiveData<List<Student>?>()
        val students = ArrayList<Student>()
        collStudent.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    students.clear()
                    for (i in snapshot.children) {
                        val student = i.getValue(Student::class.java)
                        if (student?.company?.name == companyName) {
                            students.add(student)
                        }
                    }
                    dataStudent.value = students
                } else {
                    dataStudent.value = null
                }
            }

            override fun onCancelled(error: DatabaseError) {
                dataStudent.value = null
            }
        })
        return dataStudent
    }
}