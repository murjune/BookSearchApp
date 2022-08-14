package org.techtown.booksearchapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.booksearchapp.databinding.FragmentBookBinding
import org.techtown.booksearchapp.ui.viewmodel.BookSearchViewModel

@AndroidEntryPoint
class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding ?: error("바인딩에 null이 들어감")
    private val args by navArgs<BookFragmentArgs>()
    private val viewModel by activityViewModels<BookSearchViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebPage()
        setOnClickButton()
    }

    private fun setOnClickButton() {
        binding.fabFavorite.setOnClickListener {
            val book = args.book
            viewModel.saveBook(book)
            // TODO snackbar 위치 좀 올리기
            view?.let { Snackbar.make(it, "책이 저장되었습니다.", Snackbar.LENGTH_SHORT).show() }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebPage() {
        val book = args.book

        binding.wbBook.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }
    }

    override fun onPause() {
        binding.wbBook.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.wbBook.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
