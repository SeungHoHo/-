package fastcampus.aop.aop_part5_chapter06_repeat.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.ShippingCompany
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingItem

@Database(
    entities = [TrackingItem::class, ShippingCompany::class],
    version = 1,
    exportSchema = false
)


abstract class AppDatabase : RoomDatabase() {

    abstract fun trackingItemDao(): TrackingItemDao
    abstract fun shippingCompanyDao(): ShippingCompanyDao

    companion object {

        private const val DATABASE_NAME = "tracking.db"

        fun build(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }

}