package com.example.extend1.model

data class Student(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val job: String = "",
    val age: String = "",
    val image: String = "",
    val company: Company? = null
) {
    companion object {
        fun saveStudent(
            id: String,
            email: String,
            name: String,
            password: String,
            job: String,
            age: String,
            image: String,
            company: Company,
        ): Student {
            return Student(id, email, name, password, job, age, image, company)
        }
    }
}
