package com.nrup.mykmmapp.auth.domain.model

data class AuthResultData(
    val id: Int,
    val fullName: String,
    val email: String,
    var authToken: String? = null,
    val createdAt: String
)
