package com.kky.local.database.dao

import androidx.room.Insert
import com.kky.local.database.entity.Keyword

interface KeywordDAO {
    @Insert
    fun insert(keyword: Keyword)
}