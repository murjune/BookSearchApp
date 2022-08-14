package org.techtown.booksearchapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.techtown.booksearchapp.data.repository.BookSearchRepository
import org.techtown.booksearchapp.data.repository.BookSearchRepositoryImpl

@Module // @Module : hilt가 알 수 있도록 hilt-module임을 가리킴
@InstallIn(ViewModelComponent::class) // @InstallIn : @Module로 지정된 Class는 @Installin을 지정하여 각 모듈이 어떤 Scope(activity, fragment...)에서 사용되는지 알려야함.
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookSearchRepository(
        impl: BookSearchRepositoryImpl
    ): BookSearchRepository
}
