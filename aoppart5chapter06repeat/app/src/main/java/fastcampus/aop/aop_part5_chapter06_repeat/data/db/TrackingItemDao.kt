package fastcampus.aop.aop_part5_chapter06_repeat.data.db

import androidx.room.*
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackingItemDao {

    @Query("SELECT * FROM TrackingItem")
    fun allTrackingItems(): Flow<List<TrackingItem>>

    @Query("SELECT * FROM TrackingItem")
    suspend fun getAll(): List<TrackingItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TrackingItem)

    @Delete
    suspend fun delete(item: TrackingItem)
}