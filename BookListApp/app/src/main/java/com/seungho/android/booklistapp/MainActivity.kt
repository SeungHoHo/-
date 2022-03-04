package com.seungho.android.booklistapp

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.seungho.android.booklistapp.adapter.BookAdapter
import com.seungho.android.booklistapp.api.BookAPI
import com.seungho.android.booklistapp.databinding.ActivityMainBinding
import com.seungho.android.booklistapp.model.BestSellerDto
import com.seungho.android.booklistapp.model.SearchBooksDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter

    private lateinit var service: BookAPI


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            adapter = BookAdapter(clickListener = {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("bookModel", it)
                startActivity(intent)
            })

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(BookAPI::class.java)
        service.getBestSeller(getString(R.string.interpark_apikey))
            .enqueue(object : Callback<BestSellerDto> {
                override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let {
                        adapter.submitList(it.books)
                    }
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {}

            })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.searchEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false

        }

    }

    private fun search(text: String) {


        service.getBooksByName(getString(R.string.interpark_apikey), text)
            .enqueue(object: Callback<SearchBooksDto> {
                override fun onFailure(call: Call<SearchBooksDto>, t: Throwable) {
                }

                override fun onResponse(call: Call<SearchBooksDto>, response: Response<SearchBooksDto>) {

                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let {
                        adapter.submitList(it.books)
                    }
                }

            })
    }


    companion object {
        private const val TAG = "MainActivity"
        private const val BASE_URL = "https://book.interpark.com/"
    }
}