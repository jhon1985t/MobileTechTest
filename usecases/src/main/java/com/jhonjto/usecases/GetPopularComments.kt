package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.domain.CommentsItem

class GetPopularComments(private val commentsRepository: CommentsRepository) {
    suspend fun invoke(): List<CommentsItem> = commentsRepository.getPopularComments()
}
