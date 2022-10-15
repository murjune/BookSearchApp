package org.techtown.booksearchapp.data.api

import org.techtown.booksearchapp.data.model.ResponseSearchBook
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchApi {
    @GET("v3/search/book")
    suspend fun searchBooks(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ResponseSearchBook
}
