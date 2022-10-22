package com.kky.domain.repository

import com.kky.domain.model.Keyword
import kotlinx.coroutines.flow.Flow

interface KeywordRepository {
    suspend fun insert(value: String)
    fun getAll(): Flow<List<Keyword>>
    suspend fun search(value: String): Keyword?
    suspend fun addCount(keyword: Keyword)
}