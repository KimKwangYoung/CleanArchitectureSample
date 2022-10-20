package com.kky.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keyword")
data class Keyword(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val value: String,
    val count: Int
)
