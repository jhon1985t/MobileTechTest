package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DeleteAllCommentsTest {
    @Mock
    lateinit var commentsRepository: CommentsRepository

    lateinit var deleteAllComments: DeleteAllComments

    @Before
    fun setUp() {
        deleteAllComments = DeleteAllComments(commentsRepository)
    }

    @Test
    fun `invoke calls delete all comments repository`() {
        runBlocking {

            whenever(commentsRepository.deleteAll(false)).thenReturn(Unit)

            val result = deleteAllComments.invoke()

            assertEquals(Unit, result)
        }
    }
}
