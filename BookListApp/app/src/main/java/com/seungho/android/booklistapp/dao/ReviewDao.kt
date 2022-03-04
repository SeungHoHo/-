package com.seungho.android.booklistapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seungho.android.booklistapp.model.Review

@Dao
interface ReviewDao {

    @Query("SELECT * FROM review WHERE id = :id ")
    fun getOne(id: Int): Review?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(review: Review?)


}