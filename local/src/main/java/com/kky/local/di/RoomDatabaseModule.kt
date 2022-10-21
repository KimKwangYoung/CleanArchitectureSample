package com.kky.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kky.local.database.AppDatabase
import com.kky.local.database.dao.KeywordDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RoomDatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Provides
    @Singleton
    fun providesKeywordDao(database: AppDatabase): KeywordDAO = database.keywordDao()
}