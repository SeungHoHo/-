package com.seungho.android.booklistapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.seungho.android.booklistapp.dao.ReviewDao
import com.seungho.android.booklistapp.model.Review

@Database(entities = [Review::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
}

fun getAppDatabase(context: Context): AppDatabase {

    val migration_1_2 = object : Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `REVIEW` (`id` INTEGER, `review` TEXT," + "PRIMARY KEY(`id`))")
        }

    }

    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "BookReviewDB"
    )
        .addMigrations(migration_1_2)
        .build()
}