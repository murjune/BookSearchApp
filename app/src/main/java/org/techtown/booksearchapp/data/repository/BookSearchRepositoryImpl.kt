package org.techtown.booksearchapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.techtown.booksearchapp.data.datasource.RemoteDataSource
import org.techtown.booksearchapp.data.db.BookSearchDao
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.util.UiState
import timber.log.Timber
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val bookSearchDao: BookSearchDao,
    private val remoteDataSource: RemoteDataSource
) : BookSearchRepository {

    // remote
    override fun searchBooks(query: String): Flow<UiState<List<Book>>> {
        return flow {
            runCatching { remoteDataSource.searchBooks(query) }.fold(
                {
                    val books = it.books

                    emit(UiState.Success(books))
                },
                {
                    Timber.e("error msg : ${it.message}")
                    emit(UiState.Error(it.message))
                }
            )
        }
    }

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
