package org.techtown.booksearchapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "books") // @Entity 붙여서 db에서 사용할 Entity로 만듦
@Parcelize
data class Book(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    @PrimaryKey(autoGenerate = false) // item을 구분할 고유키
    @SerializedName("isbn")
    val id: String,
    val price: Int,
    val publisher: String,
    @ColumnInfo(name = "sale_price") // snakeCase로 변환
    @SerializedName("sale_price")
    val salePrice: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
) : Parcelable
