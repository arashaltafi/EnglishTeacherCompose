package com.arash.altafi.englishteachercompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arash.altafi.englishteachercompose.data.db.FavoriteUserDao
import com.arash.altafi.englishteachercompose.model.FavoriteUserEntity

@Database(entities = [FavoriteUserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteUserDao(): FavoriteUserDao
}
