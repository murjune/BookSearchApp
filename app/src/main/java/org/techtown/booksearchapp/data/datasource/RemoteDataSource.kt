package org.techtown.booksearchapp.data.datasource

import org.techtown.booksearchapp.data.model.Book

interface RemoteDataSource {
    suspend fun searchBooks(query: String): List<Book>?
}
