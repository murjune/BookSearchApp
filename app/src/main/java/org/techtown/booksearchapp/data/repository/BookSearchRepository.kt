package org.techtown.booksearchapp.data.repository

import org.techtown.booksearchapp.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepository {
    suspend fun searchBooks(
        query: String
    ): Response<SearchResponse>
}
