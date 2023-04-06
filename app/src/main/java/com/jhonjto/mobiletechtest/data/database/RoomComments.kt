package com.jhonjto.mobiletechtest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomComments(
    val body: String,
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val userId: Int,
    val favorite: Boolean
)
