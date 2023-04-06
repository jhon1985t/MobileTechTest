package com.jhonjto.mobiletechtest.di

import com.jhonjto.data.source.source.LocalDataSource
import com.jhonjto.data.source.source.RemoteDataSource
import com.jhonjto.data.source.repository.CommentsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun moviesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = CommentsRepository(localDataSource, remoteDataSource)
}
