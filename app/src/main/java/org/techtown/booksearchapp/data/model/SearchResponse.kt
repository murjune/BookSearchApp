package org.techtown.booksearchapp.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
)