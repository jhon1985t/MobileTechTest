package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.domain.CommentsItem
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindCommentsByIdTest {
    @Mock
    lateinit var commentsRepository: CommentsRepository

    lateinit var findCommentById: FindCommentById

    @Before
    fun setUp() {
        findCommentById = FindCommentById(commentsRepository)
    }

    @Test
    fun `invoke calls find comment by id repository`() {
        runBlocking {

            val comments = mockedCommentsById

            whenever(commentsRepository.findById(1)).thenReturn(comments)

            val result = findCommentById.invoke(1)

            Assert.assertEquals(comments, result)
        }
    }
}

val mockedCommentsById = CommentsItem(
    "this is tests",
    1,
    "test",
    1,
    false
)
