package com.example.extend1.model

data class Admin(
    val id: String = "",
    val name: String = "",
    val npwp: String = "",
    val email: String = "",
    val password: String = ""
) {
    companion object {
        fun saveAdmin(
            id: String,
            nama: String,
            npwp: String,
            email: String,
            password: String
        ): Admin {
            return Admin(id, nama, npwp, email, password)
        }
    }
}