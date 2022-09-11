package org.techtown.booksearchapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.techtown.booksearchapp.data.datasource.RemoteDataSource
import org.techtown.booksearchapp.data.db.BookSearchDao
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.util.Sort
import org.techtown.booksearchapp.util.UiState
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val bookSearchDao: BookSearchDao,
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStore<Preferences>
) : BookSearchRepository {

    // remote
    override fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Flow<UiState<List<Book>>> {
        return flow {
            runCatching { remoteDataSource.searchBooks(query, sort, page, size) }.fold(
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

    // DataStore
    override suspend fun saveSortMode(mode: String) {
        dataStore.edit { prefs ->
            Timber.d("mode : $mode")
            prefs[SORT_MODE] = mode
        }
    }

    override fun getSortMode(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Timber.e(exception.message)
                    emit(emptyPreferences())
                }
            }.map { prefs ->
                prefs[SORT_MODE] ?: Sort.ACCURACY.value
            }.flowOn(Dispatchers.IO)
    }

    private companion object {
        val SORT_MODE = stringPreferencesKey("sort_mode")
    }
}
