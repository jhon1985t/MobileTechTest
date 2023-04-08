package com.jhonjto.usecases

import com.jhonjto.data.source.repository.CommentsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ToggleCommentFavoriteTest {
    @Mock
    lateinit var commentsRepository: CommentsRepository

    lateinit var toggleCommentFavorite: ToggleCommentFavorite

    @Before
    fun setUp() {
        toggleCommentFavorite = ToggleCommentFavorite(commentsRepository)
    }

    @Test
    fun `invoke calls save favorite by id repository`() {
        runBlocking {

            val comment = mockedCommentsById.copy(id = 1)

            val result = toggleCommentFavorite.invoke(comment)

            Assert.assertTrue(result.favorite)
        }
    }

    @Test
    fun `invoke calls show favorite false repository`() {
        runBlocking {

            val comment = mockedCommentsById.copy(favorite = false)

            val result = toggleCommentFavorite.invoke(comment)

            Assert.assertTrue(result.favorite)
        }
    }
}
