package org.techtown.booksearchapp.data.repository

import androidx.lifecycle.LiveData
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepository {

    // remote
    suspend fun searchBooks(
        query: String
    ): Response<SearchResponse>

    // Room

    suspend fun insertBooks(book: Book)

    suspend fun deleteBooks(book: Book)

    suspend fun getFavoriteBooks(): LiveData<List<Book>>
}
