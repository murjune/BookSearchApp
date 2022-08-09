package org.techtown.booksearchapp.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.data.model.SearchResponse
import org.techtown.booksearchapp.data.repository.BookSearchRepository
import retrofit2.Response

class BookSearchViewModel(
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // API
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    // SaveState
    var query = ""
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    // remote
    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response: Response<SearchResponse> = bookSearchRepository.searchBooks(query)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    // local
    fun saveBook(book: Book) = viewModelScope.launch {
        bookSearchRepository.insertBooks(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        bookSearchRepository.deleteBooks(book)
    }

    val favoriteBooks: LiveData<List<Book>> = bookSearchRepository.getFavoriteBooks()

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }
}
