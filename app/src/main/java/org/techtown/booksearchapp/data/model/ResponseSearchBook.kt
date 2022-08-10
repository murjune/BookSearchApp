package org.techtown.booksearchapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseSearchBook(
    @SerializedName("documents")
    val books: List<Book>,
    @SerializedName("meta")
    val meta: Meta
)
