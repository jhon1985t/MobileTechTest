package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.domain.PostCommentsItem

class GetPostCommentsByPostId(private val commentsRepository: CommentsRepository) {
    suspend fun invoke(postId: Int): List<PostCommentsItem> =
        commentsRepository.getPostComments(postId)
}
