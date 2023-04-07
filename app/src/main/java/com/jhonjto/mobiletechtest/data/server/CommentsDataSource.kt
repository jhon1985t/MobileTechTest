package com.jhonjto.mobiletechtest.data.server

import com.jhonjto.data.source.source.RemoteDataSource
import com.jhonjto.domain.CommentsItem
import com.jhonjto.domain.PostCommentsItem
import com.jhonjto.mobiletechtest.data.toDomainComments
import com.jhonjto.mobiletechtest.data.toDomainPostComments

class CommentsDataSource: RemoteDataSource {
    override suspend fun getListComments(): List<CommentsItem> =
        JSONPlaceHolder.service
            .listComments()
            .run { this.map {
                it.toDomainComments()
            } }

    override suspend fun getCommentsItem(id: Int): CommentsItem =
        JSONPlaceHolder.service
            .listCommentItem(id)
            .toDomainComments()

    override suspend fun getPostComments(postId: Int): List<PostCommentsItem> =
        JSONPlaceHolder.service
            .listCommentsByPostId(postId)
            .run { this.map {
                it.toDomainPostComments()
            } }
}
