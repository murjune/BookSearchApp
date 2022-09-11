package org.techtown.booksearchapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.techtown.booksearchapp.data.db.BookSearchDao
import org.techtown.booksearchapp.data.db.BookSearchDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    fun provideBookDao(bookSearchDatabase: BookSearchDatabase): BookSearchDao {
        return bookSearchDatabase.bookSearchDao()
    }

    // Hilt에서 항상 동일한 데이터베이스 인스턴스를 제공하도록 Singleton으로
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BookSearchDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            BookSearchDatabase::class.java,
            "favorite-books"
        ).build()
    }
}
