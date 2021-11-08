package com.example.shahrytask.di

import android.app.Application
import androidx.room.Room
import com.example.shahrytask.db.ShahryDao
import com.example.shahrytask.db.ShahryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideShahryDb(application: Application):ShahryDb=
        Room.databaseBuilder(application.applicationContext,
        ShahryDb::class.java,"shahry_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()


    @Provides
    @Singleton
    fun provideShahryDao(shahryDb: ShahryDb):ShahryDao=
        shahryDb.ShahryDao()
}