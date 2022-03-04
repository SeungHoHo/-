package com.seungho.android.booklistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.seungho.android.booklistapp.databinding.ActivityDetailBinding
import com.seungho.android.booklistapp.model.Book
import com.seungho.android.booklistapp.model.Review

class DetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = getAppDatabase(this)


        val bookModel = intent.getParcelableExtra<Book>("bookModel")

        binding.backButton.setOnClickListener {
            finish()
        }
        binding.titleTextView.text = bookModel?.title.orEmpty()

        Glide
            .with(binding.coverImageView.context)
            .load(bookModel?.coverSmallUrl.orEmpty())
            .into(binding.coverImageView)

        binding.descriptionTextView.text = bookModel?.description.orEmpty()
        binding.authorTextView.text = bookModel?.author.orEmpty()
        binding.priceTextView.text = bookModel?.priceSales.orEmpty()
        binding.sellingTextView.text = bookModel?.saleStatus.orEmpty()
        binding.publishTextView.text = bookModel?.publisher.orEmpty()

        Thread {
            val review = db.reviewDao().getOne(bookModel?.id?.toInt() ?: 0)
            runOnUiThread {
                binding.reviewTextView.setText(review?.review.orEmpty())
            }
        }.start()

        binding.reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("bookModel", bookModel)
            startActivityForResult(intent, 99)
        }

        binding.saveButton.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(
                        bookModel?.id?.toInt() ?: 0,
                        binding.reviewTextView.text.toString()
                    )
                )
            }.start()
            Toast.makeText(this, "저장을 완료했습니다", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when(requestCode) {
                99 -> {
                    val reviews = data?.getStringExtra("review")
                    binding.reviewTextView.text = reviews
                }
            }
        }
    }


}