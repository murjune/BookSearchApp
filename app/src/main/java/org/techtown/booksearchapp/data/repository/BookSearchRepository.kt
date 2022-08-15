package org.techtown.booksearchapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.techtown.booksearchapp.data.model.Book

interface BookSearchRepository {

    // remote
    suspend fun searchBooks(
        query: String
    ): List<Book>?

    // Room
    suspend fun insertBooks(book: Book)

    suspend fun deleteBooks(book: Book)

    fun getFavoriteBooks(): Flow<List<Book>>
}
