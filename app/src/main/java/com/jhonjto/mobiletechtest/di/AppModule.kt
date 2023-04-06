package com.jhonjto.mobiletechtest.di

import android.app.Application
import androidx.room.Room
import com.jhonjto.data.source.LocalDataSource
import com.jhonjto.data.source.RemoteDataSource
import com.jhonjto.mobiletechtest.data.database.CommentsDatabase
import com.jhonjto.mobiletechtest.data.database.RoomDataSource
import com.jhonjto.mobiletechtest.data.server.CommentsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        CommentsDatabase::class.java,
        "movie-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: CommentsDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = CommentsDataSource()
}
