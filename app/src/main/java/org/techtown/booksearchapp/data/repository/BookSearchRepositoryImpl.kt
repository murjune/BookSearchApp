package org.techtown.booksearchapp.data.repository

import androidx.lifecycle.LiveData
import org.techtown.booksearchapp.data.api.SearchBookClient
import org.techtown.booksearchapp.data.db.BookSearchDatabase
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl(
    private val db: BookSearchDatabase
) : BookSearchRepository {

    // remote
    override suspend fun searchBooks(query: String): Response<SearchResponse> {
        return SearchBookClient.bookSearchApi.searchBooks(query)
    }

    // Room
    override suspend fun insertBooks(book: Book) {
        db.bookSearchDao().insertBook(book)
    }

    override suspend fun deleteBooks(book: Book) {
        db.bookSearchDao().deleteBook(book)
    }

    override fun getFavoriteBooks(): LiveData<List<Book>> {
        return db.bookSearchDao().getFavoriteBooks()
    }
    // api -> repository -> model -> view
}
