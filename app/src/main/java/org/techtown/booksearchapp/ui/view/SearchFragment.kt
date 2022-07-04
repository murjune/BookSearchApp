package org.techtown.booksearchapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import org.techtown.booksearchapp.databinding.FragmentSearchBinding
import org.techtown.booksearchapp.ui.adapter.BookSearchAdapter
import org.techtown.booksearchapp.ui.viewmodel.BookSearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding ?: error("바인딩에 null값 들어감")
    private lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var bookSearchAdapter: BookSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel
        setUpAdapter()
        searchBooks()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpAdapter() {

        bookSearchAdapter = BookSearchAdapter()
        binding.rvSearch.apply {
//            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = bookSearchAdapter
        }
    }

    private fun searchBooks() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.edtSearchImage.text =
            Editable.Factory.getInstance().newEditable(bookSearchViewModel.query)

        binding.edtSearchImage.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= SEARCH_BOOKS_TIME_DELAY) {
                text?.let {
                    val query = it.toString().trim()
                    if (query.isNotBlank()) {
                        bookSearchViewModel.searchBooks(query)
                        bookSearchViewModel.query = query
                    }
                }
            }
        }
    }

    private fun initView() {
        bookSearchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val books = response.books
            bookSearchAdapter.submitList(books.toMutableList())
        }
    }

    companion object {
        private const val SEARCH_BOOKS_TIME_DELAY = 100L
    }
}
