package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository

class DeleteAllComments(private val commentsRepository: CommentsRepository) {
    suspend fun invoke() = commentsRepository.deleteAll(false)
}
