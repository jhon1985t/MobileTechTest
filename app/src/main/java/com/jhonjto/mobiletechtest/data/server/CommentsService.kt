package com.jhonjto.mobiletechtest.data.server

import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsService {
    @GET("posts")
    suspend fun listComments(): CommentsResultResponse

    @GET("posts/{id}")
    suspend fun listCommentItem(@Path("id") searchById: Int): CommentsResultResponseItem

    @GET("posts/{postId}/comments")
    suspend fun listCommentsByPostId(@Path("postId") postId: Int): PostCommentsResponse
}
