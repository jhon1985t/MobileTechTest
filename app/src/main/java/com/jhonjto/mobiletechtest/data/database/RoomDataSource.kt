package com.jhonjto.mobiletechtest.data.database

import com.jhonjto.data.source.source.LocalDataSource
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.data.toRoomComments
import com.jhonjto.mobiletechtest.data.toDomainCommentsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: CommentsDatabase): LocalDataSource {

    private val commentsDao = db.commentsDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) {
            commentsDao.commentsCount() <= 0
        }

    override suspend fun saveComments(commentsItem: List<CommentsItem>) {
        withContext(Dispatchers.IO) {
            commentsDao.insertComments(commentsItem.map { it.toRoomComments() })
        }
    }

    override suspend fun getPopularComments(): List<CommentsItem> =
        withContext(Dispatchers.IO) {
            commentsDao.getAll().map { it.toDomainCommentsItem() }
        }

    override suspend fun findById(id: Int): CommentsItem =
        withContext(Dispatchers.IO) {
            commentsDao.findById(id).toDomainCommentsItem()
        }

    override suspend fun update(commentsItem: CommentsItem) {
        withContext(Dispatchers.IO) {
            commentsDao.updateComments(commentsItem.toRoomComments())
        }
    }

    override suspend fun deleteById(id: Int): Unit =
        withContext(Dispatchers.IO) {
            commentsDao.deleteByUserId(id)
        }

    override suspend fun deleteAll(favorite: Boolean): Unit =
        withContext(Dispatchers.IO) {
            commentsDao.deleteAll(favorite)
        }
}
