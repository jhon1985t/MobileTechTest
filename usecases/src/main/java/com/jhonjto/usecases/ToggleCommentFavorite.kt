package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.domain.CommentsItem

class ToggleCommentFavorite(private val commentsRepository: CommentsRepository) {
    suspend fun invoke(commentsItem: CommentsItem): CommentsItem = with(commentsItem) {
        copy(favorite = !favorite).also { commentsRepository.update(it) }
    }
}
