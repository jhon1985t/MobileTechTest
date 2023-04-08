package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DeleteCommentsByIdTest {
    @Mock
    lateinit var commentsRepository: CommentsRepository

    lateinit var deleteCommentsById: DeleteCommentsById

    @Before
    fun setUp() {
        deleteCommentsById = DeleteCommentsById(commentsRepository)
    }

    @Test
    fun `invoke calls delete comment by id repository`() {
        runBlocking {

            whenever(commentsRepository.deleteById(1)).thenReturn(Unit)

            val result = deleteCommentsById.invoke(1)

            Assert.assertEquals(Unit, result)
        }
    }
}
