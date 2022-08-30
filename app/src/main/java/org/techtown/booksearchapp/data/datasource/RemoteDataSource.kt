package org.techtown.booksearchapp.data.datasource

import org.techtown.booksearchapp.data.model.ResponseSearchBook

interface RemoteDataSource {
    suspend fun searchBooks(query: String): ResponseSearchBook
}
