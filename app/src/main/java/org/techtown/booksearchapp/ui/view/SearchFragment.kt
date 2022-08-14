package org.techtown.booksearchapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.booksearchapp.databinding.FragmentSearchBinding
import org.techtown.booksearchapp.ui.adapter.BookSearchAdapter
import org.techtown.booksearchapp.ui.viewmodel.BookSearchViewModel

@AndroidEntryPoint
class SearchFragment : androidx.fragment.app.Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding ?: error("바인딩에 null값 들어감")

    val bookSearchViewModel by activityViewModels<BookSearchViewModel>()
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
        setUpAdapter()
        searchBooks()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickItem(position: Int) {
        val action =
            SearchFragmentDirections.actionFragmentSearchToFragmentBook(bookSearchAdapter.currentList[position])
        findNavController().navigate(action)
    }

    private fun setUpAdapter() {

        bookSearchAdapter = BookSearchAdapter(::clickItem)
        binding.rvSearch.apply {
            setHasFixedSize(true)
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
        bookSearchViewModel.searchResult.observe(viewLifecycleOwner) { bookList ->
            bookSearchAdapter.submitList(bookList.toList())
        }
    }

    companion object {
        private const val SEARCH_BOOKS_TIME_DELAY = 100L
    }
}
