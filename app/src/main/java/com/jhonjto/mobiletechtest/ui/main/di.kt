package com.jhonjto.mobiletechtest.ui.main

import com.jhonjto.data.source.repository.CommentsRepository
import com.jhonjto.usecases.DeleteAllComments
import com.jhonjto.usecases.GetPopularComments
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getPopularCommentsProvider(commentsRepository: CommentsRepository) =
        GetPopularComments(commentsRepository)

    @Provides
    @ViewModelScoped
    fun deleteAllCommentsProvider(commentsRepository: CommentsRepository) =
        DeleteAllComments(commentsRepository)
}
