package org.techtown.booksearchapp.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.data.repository.BookSearchRepository
import org.techtown.booksearchapp.util.Sort
import org.techtown.booksearchapp.util.UiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _sortMode = MutableStateFlow<String>(Sort.ACCURACY.value)
    val sortMode: StateFlow<String> = _sortMode.asStateFlow()

    init {
        viewModelScope.launch {
            _sortMode.value = bookSearchRepository.getSortMode().first()
            Timber.d("init --- sortMode")
        }
    }

    // SaveState
    var query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    // API
    private var _searchResult: MutableStateFlow<UiState<List<Book>>> =
        MutableStateFlow(UiState.Loading)
    val searchResult: StateFlow<UiState<List<Book>>> get() = _searchResult.asStateFlow()

    fun searchBook(query: String) {
        viewModelScope.launch {
            bookSearchRepository.searchBooks(query, sortMode.value, 1, 15).collect {
                _searchResult.value = it
            }
        }
    }

    // Flow -> StateFlow 변환작업
    val favoriteBooks: StateFlow<List<Book>> = bookSearchRepository
        .getFavoriteBooks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    // local
    fun saveBook(book: Book) = viewModelScope.launch {
        bookSearchRepository.insertBooks(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        bookSearchRepository.deleteBooks(book)
    }

    // dataStore
    fun saveSortMode(value: String) = viewModelScope.launch {
        _sortMode.value = value
        Timber.d("sortMode : ${sortMode.value}")
        bookSearchRepository.saveSortMode(value)
    }

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }
}
