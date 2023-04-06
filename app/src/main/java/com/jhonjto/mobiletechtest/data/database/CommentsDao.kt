package com.jhonjto.mobiletechtest.data.database

import androidx.room.*

@Dao
interface CommentsDao {

    @Query("SELECT * FROM RoomComments")
    fun getAll(): List<RoomComments>

    @Query("SELECT * FROM RoomComments WHERE id = :id")
    fun findById(id: Int): RoomComments

    @Query("SELECT COUNT(id) FROM RoomComments")
    fun commentsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComments(comments: List<RoomComments>)

    @Update
    fun updateComments(comments: RoomComments)

    @Query("DELETE FROM RoomComments WHERE id = :id")
    fun deleteByUserId(id: Int)

    @Query("DELETE FROM RoomComments WHERE favorite = :favorite")
    fun deleteAll(favorite: Boolean)
}
