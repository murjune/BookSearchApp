package org.techtown.booksearchapp.data.repository

import org.techtown.booksearchapp.data.api.SearchBookClient
import org.techtown.booksearchapp.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl : BookSearchRepository {
    override suspend fun searchBooks(query: String): Response<SearchResponse> {
        return SearchBookClient.bookSearchApi.searchBooks(query)
    }
    // api -> repository -> model -> view
}
