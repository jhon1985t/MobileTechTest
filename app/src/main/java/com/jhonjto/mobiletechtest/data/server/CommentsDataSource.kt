package com.jhonjto.mobiletechtest.data.server

import com.jhonjto.data.source.source.RemoteDataSource
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.data.toDomainComments

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
}
