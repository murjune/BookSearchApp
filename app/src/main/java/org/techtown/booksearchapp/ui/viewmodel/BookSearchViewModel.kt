package org.techtown.booksearchapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.booksearchapp.data.model.SearchResponse
import org.techtown.booksearchapp.data.repository.BookSearchRepository
import retrofit2.Response

class BookSearchViewModel(
    private val bookSearchRepository: BookSearchRepository
) : ViewModel() {

    // API
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response: Response<SearchResponse> = bookSearchRepository.searchBooks(query)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }
}
