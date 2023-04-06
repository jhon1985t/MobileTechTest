package com.jhonjto.data.source

import com.jhonjto.domain.CommentsItem

interface RemoteDataSource {
    suspend fun getListComments(): List<CommentsItem>

    suspend fun getCommentsItem(): CommentsItem
}
