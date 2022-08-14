package org.techtown.booksearchapp.data.repository

import androidx.lifecycle.LiveData
import org.techtown.booksearchapp.data.model.Book

interface BookSearchRepository {

    // remote
    suspend fun searchBooks(
        query: String
    ): List<Book>?

    // Room
    suspend fun insertBooks(book: Book)

    suspend fun deleteBooks(book: Book)

    fun getFavoriteBooks(): LiveData<List<Book>>
}
