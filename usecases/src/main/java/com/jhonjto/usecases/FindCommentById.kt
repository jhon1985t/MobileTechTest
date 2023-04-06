package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.domain.CommentsItem

class FindCommentById(private val commentsRepository: CommentsRepository) {
    suspend fun invoke(id: Int): CommentsItem = commentsRepository.findById(id)
}
