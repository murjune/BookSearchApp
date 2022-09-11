package org.techtown.booksearchapp.data.datasource

import org.techtown.booksearchapp.data.api.BookSearchApi
import org.techtown.booksearchapp.data.model.ResponseSearchBook
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val bookSearchApi: BookSearchApi
) : RemoteDataSource {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): ResponseSearchBook =
        bookSearchApi.searchBooks(
            query,
            sort,
            page,
            size
        )
}
