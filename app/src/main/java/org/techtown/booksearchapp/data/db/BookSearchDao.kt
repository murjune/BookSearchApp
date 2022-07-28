package org.techtown.booksearchapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.techtown.booksearchapp.data.model.Book

@Dao
interface BookSearchDao {

    // 동일한 "id"값을 갖는다면 덮어쓰기
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM books") // Select (전체) From (Entity Table : books)
    suspend fun getFavoriteBooks(): LiveData<List<Book>>
}
