package com.jhonjto.mobiletechtest.data.server

data class PostCommentsResponseItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)
