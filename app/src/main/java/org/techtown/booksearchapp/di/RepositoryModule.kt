package org.techtown.booksearchapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.techtown.booksearchapp.data.repository.BookSearchRepository
import org.techtown.booksearchapp.data.repository.BookSearchRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRulesTodayRepository(
        impl: BookSearchRepositoryImpl
    ): BookSearchRepository
}
