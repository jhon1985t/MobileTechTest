package com.jhonjto.mobiletechtest.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.usecases.FindCommentById
import com.jhonjto.usecases.ToggleCommentFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailActivityModule {

    @Provides
    fun findMovieByIdProvider(commentsRepository: CommentsRepository) = FindCommentById(commentsRepository)

    @Provides
    fun toggleCommentFavoriteProvider(commentsRepository: CommentsRepository) =
        ToggleCommentFavorite(commentsRepository)

    @Provides
    @Named("id")
    fun commentIdProvider(stateHandle: SavedStateHandle): Int =
        stateHandle.get<Int>(DetailActivity.COMMENT)
            ?: throw IllegalStateException("Comment Id not found in the state handle")
}
