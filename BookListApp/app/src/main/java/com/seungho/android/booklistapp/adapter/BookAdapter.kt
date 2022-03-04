package com.seungho.android.booklistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seungho.android.booklistapp.databinding.ItemBookBinding
import com.seungho.android.booklistapp.model.Book

class BookAdapter(val clickListener: (Book) -> Unit) : ListAdapter<Book, BookAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bookModel: Book) {
            binding.titleTextView.text = bookModel.title
            binding.authorTextView.text = bookModel.author
            binding.priceTextView.text = bookModel.priceSales
            binding.sellingTextView.text = bookModel.saleStatus

            Glide
                .with(binding.coverImageView.context)
                .load(bookModel.coverSmallUrl)
                .into(binding.coverImageView)

            binding.root.setOnClickListener {
                clickListener(bookModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
            //현재 리스트에 노출하고 있는 아이템과 새로운 아이템이 서로 같은지 비교한다. 보통 고유한 ID 값을 체크한다.
            override fun areItemsTheSame(oldItem: Book, newItem: Book) =
                oldItem == newItem
            //현재 리스트에 노출하고 있는 아이템과 새로운 아이템의 equals를 비교한다.
            override fun areContentsTheSame(oldItem: Book, newItem: Book) =
                oldItem.id == newItem.id

        }
    }


}