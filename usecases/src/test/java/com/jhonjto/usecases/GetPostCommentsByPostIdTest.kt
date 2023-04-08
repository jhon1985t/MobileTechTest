package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.domain.PostCommentsItem
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPostCommentsByPostIdTest {
    @Mock
    lateinit var commentsRepository: CommentsRepository

    lateinit var getPostCommentsByPostId: GetPostCommentsByPostId

    @Before
    fun setUp() {
        getPostCommentsByPostId = GetPostCommentsByPostId(commentsRepository)
    }

    @Test
    fun `invoke calls popular comment by id repository`() {
        runBlocking {

            val comments = listOf(mockedPostCommentsItem)

            whenever(commentsRepository.getPostComments(1)).thenReturn(comments)

            val result = getPostCommentsByPostId.invoke(1)

            Assert.assertEquals(comments, result)
        }
    }
}

val mockedPostCommentsItem = PostCommentsItem(
    body = "this is a test",
    email = "fake@gmail.com",
    id = 1,
    name = "fake",
    postId = 1
)
