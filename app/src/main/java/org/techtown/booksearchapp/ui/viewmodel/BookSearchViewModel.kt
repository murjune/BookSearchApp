package org.techtown.booksearchapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.data.repository.BookSearchRepository
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // API
    private val _searchResult = MutableLiveData<List<Book>>()
    val searchResult: LiveData<List<Book>> get() = _searchResult

    // SaveState
    var query = ""
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    val favoriteBooks: Flow<List<Book>> = bookSearchRepository.getFavoriteBooks()

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    // remote
    fun searchBooks(query: String) = viewModelScope.launch {
        bookSearchRepository.searchBooks(query)?.let {
            _searchResult.postValue(it)
        }
    }

    // local
    fun saveBook(book: Book) = viewModelScope.launch {
        bookSearchRepository.insertBooks(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        bookSearchRepository.deleteBooks(book)
    }

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }
}
