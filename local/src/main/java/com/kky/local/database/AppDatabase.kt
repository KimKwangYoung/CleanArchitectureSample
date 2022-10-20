package com.kky.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kky.local.database.dao.KeywordDAO
import com.kky.local.database.entity.Keyword

@Database(entities = [Keyword::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun keywordDao(): KeywordDAO
}