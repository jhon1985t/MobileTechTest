package com.jhonjto.domain

data class CommentsItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val favorite: Boolean
)
