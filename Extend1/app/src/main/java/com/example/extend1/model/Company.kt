package com.example.extend1.model

data class Company(
    val id: String = "",
    val name: String = "",
    val npwp: String = "",
    val email: String = "",
    val password: String = ""
) {
    companion object {
        fun saveCompany(
            id: String,
            nama: String,
            npwp: String,
            email: String,
            password: String
        ): Company {
            return Company(id, nama, npwp, email, password)
        }
    }
}