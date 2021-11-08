package com.example.shahrytask.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Authorss::class,Post::class],version = 1)
abstract class ShahryDb : RoomDatabase() {
    abstract fun ShahryDao():ShahryDao
}