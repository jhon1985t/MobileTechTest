package com.jhonjto.data.source

import com.jhonjto.domain.CommentsItem

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveComments(commentsItem: List<CommentsItem>)
    suspend fun getPopularComments(): List<CommentsItem>
    suspend fun findById(id: Int): CommentsItem
    suspend fun update(commentsItem: CommentsItem)
    suspend fun deleteById(id: Int)
    suspend fun deleteAll(favorite: Boolean)
}
