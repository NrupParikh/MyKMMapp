package com.nrup.mykmmapp.auth.data

import kotlinx.serialization.Serializable

// register
@Serializable
internal data class SignUpRequest(
    val fullName: String,
    val email: String,
    val password: String,
)

// login
@Serializable
internal data class SignInRequest(val email: String, val password: String)

// ============== common structure of API response
@Serializable
internal data class AuthResponse(
    val data: AuthResponseData? = null,
    val errorMessage: String? = null
)

@Serializable
internal data class AuthResponseData(
    val id: Int,
    val fullName: String,
    val email: String,
    var authToken: String,
    val createdAt: String
)

// =============== POST
@Serializable
data class PostResponseData(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val createdAt: String
)

// ==== post list

@Serializable
data class PostListResponse(
    val data: List<PostResponseData> = listOf(),
    val errorMessage: String? = null
)