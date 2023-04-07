package com.jhonjto.data.source.source

import com.jhonjto.domain.CommentsItem
import com.jhonjto.domain.PostCommentsItem

interface RemoteDataSource {
    suspend fun getListComments(): List<CommentsItem>

    suspend fun getCommentsItem(id: Int): CommentsItem

    suspend fun getPostComments(postId: Int): List<PostCommentsItem>
}
