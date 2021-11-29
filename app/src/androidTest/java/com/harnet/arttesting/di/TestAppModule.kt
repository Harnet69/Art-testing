package com.harnet.arttesting.di

import android.content.Context
import androidx.room.Room
import com.harnet.arttesting.roomDb.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {


    @Singleton
    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoomDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}