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
class GetPopularCommentsTest {
    @Mock
    lateinit var commentsRepository: CommentsRepository

    lateinit var getPopularComments: GetPopularComments

    @Before
    fun setUp() {
        getPopularComments = GetPopularComments(commentsRepository)
    }

    @Test
    fun `invoke calls popular comments repository`() {
        runBlocking {

            val comments = listOf(mockedCommentsById)

            whenever(commentsRepository.getPopularComments()).thenReturn(comments)

            val result = getPopularComments.invoke()

            Assert.assertEquals(comments, result)
        }
    }
}
