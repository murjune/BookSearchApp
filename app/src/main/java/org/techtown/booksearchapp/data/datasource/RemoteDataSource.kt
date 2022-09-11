package org.techtown.booksearchapp.data.datasource

import org.techtown.booksearchapp.data.model.ResponseSearchBook

interface RemoteDataSource {
    suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): ResponseSearchBook
}
