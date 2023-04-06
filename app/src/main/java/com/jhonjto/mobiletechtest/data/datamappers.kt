package com.jhonjto.mobiletechtest.data

import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.data.database.RoomComments
import com.jhonjto.mobiletechtest.data.server.CommentsResultResponseItem

fun CommentsResultResponseItem.toDomainComments(): CommentsItem = CommentsItem (
    body,
    id,
    title,
    userId,
    favorite = false
)

fun CommentsItem.toRoomComments(): RoomComments = RoomComments(
    body,
    id,
    title,
    userId,
    favorite = false
)

fun RoomComments.toDomainCommentsItem(): CommentsItem = CommentsItem(
    body,
    id,
    title,
    userId,
    favorite = false
)
