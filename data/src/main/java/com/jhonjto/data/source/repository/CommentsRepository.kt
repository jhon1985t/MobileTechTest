package com.jhonjto.data.source.repository

import com.jhonjto.data.source.source.LocalDataSource
import com.jhonjto.data.source.source.RemoteDataSource
import com.jhonjto.domain.CommentsItem
import com.jhonjto.domain.PostCommentsItem

class CommentsRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPopularComments(): List<CommentsItem> {

        if (localDataSource.isEmpty()) {
            val comments =
                remoteDataSource.getListComments()
            localDataSource.saveComments(comments)
        }

        return localDataSource.getPopularComments()
    }

    suspend fun findById(id: Int): CommentsItem = localDataSource.findById(id)

    suspend fun update(commentsItem: CommentsItem) = localDataSource.update(commentsItem)

    suspend fun getPostComments(postId: Int): List<PostCommentsItem> =
        remoteDataSource.getPostComments(postId)
}
