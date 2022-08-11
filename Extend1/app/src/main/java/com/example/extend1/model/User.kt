package com.example.extend1.model

data class User(
    val id: String = "",
    val email: String = "",
    val password: String = "",
    val name: String = ""
) {
    companion object {
        fun saveUser(id: String, email: String, password: String, name: String): User {
            return User(id, email, password, name)
        }
    }
}
