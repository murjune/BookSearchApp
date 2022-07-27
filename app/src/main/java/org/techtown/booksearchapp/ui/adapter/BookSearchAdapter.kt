package org.techtown.booksearchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.techtown.booksearchapp.data.model.Book
import org.techtown.booksearchapp.databinding.ItemBookSampleBinding

class BookSearchAdapter(val clickItem: (Int) -> Unit) :
    ListAdapter<Book, BookSearchAdapter.BookSearchViewHolder>(bookDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        return BookSearchViewHolder(
            clickItem,
            ItemBookSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        val book = currentList[position]
        holder.onBind(book)
    }

    class BookSearchViewHolder(
        private val clickItem: (Int) -> Unit,
        private val binding: ItemBookSampleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(book: Book) {
            val author: String = book.authors.toString().removeSurrounding("[", "]")
            val publisher: String = book.publisher
            val date: String =
                if (book.datetime.isNotEmpty()) book.datetime.substring(0..9) else ""
            binding.ivArticleImage.load(book.thumbnail)
            binding.tvTitle.text = book.title
            binding.tvAuthor.text = "$author | $publisher"
            binding.tvDatetime.text = date
            binding.clBook.setOnClickListener {
                clickItem(adapterPosition)
            }
        }
    }

    companion object {
        private val bookDiffUtilCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}
