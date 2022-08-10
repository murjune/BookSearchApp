package org.techtown.booksearchapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.techtown.booksearchapp.data.api.BookSearchApi
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Singleton
    @Provides
    fun provideBookSearchApi(retrofit: Retrofit): BookSearchApi =
        retrofit.create(BookSearchApi::class.java)
}
