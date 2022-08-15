package org.techtown.booksearchapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.techtown.booksearchapp.data.datasource.RemoteDataSource
import org.techtown.booksearchapp.data.db.BookSearchDao
import org.techtown.booksearchapp.data.model.Book
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val bookSearchDao: BookSearchDao,
    private val remoteDataSource: RemoteDataSource
) : BookSearchRepository {

    // remote
    override suspend fun searchBooks(query: String): List<Book>? =
        remoteDataSource.searchBooks(query)

    // Room
    override suspend fun insertBooks(book: Book) {
        bookSearchDao.insertBook(book)
    }

    override suspend fun deleteBooks(book: Book) {
        bookSearchDao.deleteBook(book)
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return bookSearchDao.getFavoriteBooks()
    }
}
