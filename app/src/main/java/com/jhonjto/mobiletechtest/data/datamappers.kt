package com.jhonjto.mobiletechtest.data

import com.jhonjto.domain.CommentsItem
import com.jhonjto.domain.PostCommentsItem
import com.jhonjto.mobiletechtest.data.database.RoomComments
import com.jhonjto.mobiletechtest.data.server.CommentsResultResponseItem
import com.jhonjto.mobiletechtest.data.server.PostCommentsResponseItem

fun CommentsResultResponseItem.toDomainComments(): CommentsItem = CommentsItem (
    body,
    id,
    title,
    userId,
    false
)

fun PostCommentsResponseItem.toDomainPostComments(): PostCommentsItem = PostCommentsItem (
    body,
    email,
    id,
    name,
    postId
)

fun CommentsItem.toRoomComments(): RoomComments = RoomComments (
    body,
    id,
    title,
    userId,
    favorite
)

fun RoomComments.toDomainCommentsItem(): CommentsItem = CommentsItem (
    body,
    id,
    title,
    userId,
    favorite
)
