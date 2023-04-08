package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository

class DeleteCommentsById(private val commentsRepository: CommentsRepository) {
    suspend fun invoke(id: Int) = commentsRepository.deleteById(id)
}
