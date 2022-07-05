package org.techtown.booksearchapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import org.techtown.booksearchapp.R
import org.techtown.booksearchapp.data.repository.BookSearchRepositoryImpl
import org.techtown.booksearchapp.databinding.ActivityMainBinding
import org.techtown.booksearchapp.ui.viewmodel.BookSearchViewModel
import org.techtown.booksearchapp.ui.viewmodel.BookSearchViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val bookSearchViewModel by viewModels<BookSearchViewModel>() {
        val bookSearchRepository = BookSearchRepositoryImpl()
        BookSearchViewModelFactory(bookSearchRepository, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvMain.setupWithNavController(navController)
    }
}
