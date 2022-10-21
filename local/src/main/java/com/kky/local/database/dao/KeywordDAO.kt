package com.kky.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kky.local.database.entity.KeywordHistory
import kotlinx.coroutines.flow.Flow

@Dao
internal interface KeywordDAO {
    @Insert
    fun insert(keyword: KeywordHistory)

    @Query("SELECT * FROM keyword ORDER BY count DESC")
    fun getAll(): Flow<List<KeywordHistory>>

    @Query("SELECT * FROM keyword WHERE :value = value")
    fun search(value: String): KeywordHistory?
}