package com.kky.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kky.local.database.dao.KeywordDAO
import com.kky.local.database.entity.KeywordHistory

@Database(entities = [KeywordHistory::class], version = 1)
internal abstract class AppDatabase(): RoomDatabase() {
    abstract fun keywordDao(): KeywordDAO
}