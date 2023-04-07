package com.jhonjto.domain

data class PostCommentsItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)
