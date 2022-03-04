package com.seungho.android.booklistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.seungho.android.booklistapp.databinding.ActivityReviewBinding
import com.seungho.android.booklistapp.model.Book
import com.seungho.android.booklistapp.model.Review

class ReviewActivity: AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bookModel = intent.getParcelableExtra<Book>("bookModel")

        binding.bookTitleTextView.text = bookModel?.title.orEmpty()

        Glide
            .with(binding.coverImageView.context)
            .load(bookModel?.coverSmallUrl.orEmpty())
            .into(binding.coverImageView)

        binding.authorTextView.text = bookModel?.author.orEmpty()
        binding.priceTextView.text = bookModel?.priceSales.orEmpty()
        binding.sellingTextView.text = bookModel?.saleStatus.orEmpty()

        binding.saveButton.setOnClickListener {
            Toast.makeText(this, "리뷰를 성공적으로 저장했습니다.", Toast.LENGTH_SHORT).show()
            val returnIntent = Intent()
            returnIntent.putExtra("review", binding.reviewEditText.text.toString())
            setResult(RESULT_OK, returnIntent)
            finish()
        }


    }


}