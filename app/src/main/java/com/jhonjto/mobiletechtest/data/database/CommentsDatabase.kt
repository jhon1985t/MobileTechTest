package com.jhonjto.mobiletechtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomComments::class], version = 1)
abstract class CommentsDatabase: RoomDatabase() {

    abstract fun commentsDao(): CommentsDao
}