package org.techtown.booksearchapp.data.api

import org.techtown.booksearchapp.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchApi {
    @GET("v3/search/book")
    suspend fun searchBooks(
        @Query("query") query: String
    ): Response<SearchResponse>
}
