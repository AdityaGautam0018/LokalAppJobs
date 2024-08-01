package com.example.lokaljobs

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [JobEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}