package org.techtown.booksearchapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.util.UiState

interface BookSearchRepository {

    // remote
    fun searchBooks(
        query: String
    ): Flow<UiState<List<Book>>>

    // Room
    suspend fun insertBooks(book: Book)

    suspend fun deleteBooks(book: Book)

    fun getFavoriteBooks(): Flow<List<Book>>
}
