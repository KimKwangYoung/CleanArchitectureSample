package com.kky.local.repository

import com.kky.domain.model.Keyword
import com.kky.domain.repository.KeywordRepository
import com.kky.local.database.dao.KeywordDAO
import com.kky.local.database.entity.KeywordHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class KeywordRepositoryImpl @Inject constructor(
    private val keywordDao: KeywordDAO
    ): KeywordRepository {

    override suspend fun insert(value: String) {
        keywordDao.insert(
            KeywordHistory(
                value = value,
                count = 1
            )
        )
    }

    override fun getAll(): Flow<List<Keyword>> {
        return keywordDao.getAll()
            .map { it.map { history -> Keyword(
                id = history.id,
                value = history.value,
                count = history.count
            ) } }
    }

    override suspend fun search(value: String): Keyword? {
        val history = keywordDao.search(value)
        val keyword: Keyword? = history?.let {
            Keyword(
                it.id,
                it.value,
                it.count
            )
        }

        return keyword
    }
}