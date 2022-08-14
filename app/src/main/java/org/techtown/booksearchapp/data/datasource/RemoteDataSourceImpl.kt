package org.techtown.booksearchapp.data.datasource

import org.techtown.booksearchapp.data.api.BookSearchApi
import org.techtown.booksearchapp.data.model.Book
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val bookSearchApi: BookSearchApi
) : RemoteDataSource {
    override suspend fun searchBooks(query: String): List<Book>? =
        runCatching { bookSearchApi.searchBooks(query) }.fold(
            { it.books },
            {
                Timber.e(it.message)
                null
            }
        )
}
