package com.jhonjto.data

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.data.source.source.LocalDataSource
import com.jhonjto.data.source.source.RemoteDataSource
import com.jhonjto.domain.CommentsItem
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CommentsRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var commentsRepository: CommentsRepository

    @Before
    fun setUp() {
        commentsRepository =
            CommentsRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `getPopularComments gets from local data source first`() {
        runBlocking {

            val localCommentsItem = listOf(mockedCommentsItem.copy(id = 1))
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getPopularComments()).thenReturn(localCommentsItem)

            val result = commentsRepository.getPopularComments()

            assertEquals(localCommentsItem, result)
        }
    }

    @Test
    fun `getPopularComments saves remote data to local`() {
        runBlocking {

            val remoteCommentsItem = listOf(mockedCommentsItem.copy(id = 2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getListComments()).thenReturn(remoteCommentsItem)

            commentsRepository.getPopularComments()

            verify(localDataSource).saveComments(remoteCommentsItem)
        }
    }

    @Test
    fun `findById calls local data source`() {
        runBlocking {

            val commentsItem = mockedCommentsItem.copy(id = 5)
            whenever(localDataSource.findById(5)).thenReturn(commentsItem)

            val result = commentsRepository.findById(5)

            assertEquals(commentsItem, result)
        }
    }

    @Test
    fun `update updates local data source`() {
        runBlocking {

            val commentsItem = mockedCommentsItem.copy(id = 1)

            commentsRepository.update(commentsItem)

            verify(localDataSource).update(commentsItem)
        }
    }
}

val mockedCommentsItem = CommentsItem(
    "this is tests",
    1,
    "test",
    1,
    false
)
