package fastcampus.aop.aop_part5_chapter06_repeat.data.repository


import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingInformation
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingItem
import kotlinx.coroutines.flow.Flow

interface TrackingItemRepository {

    val trackingItems: Flow<List<TrackingItem>>

    suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>>

    suspend fun getTrackingInformation(companyCode: String, invoice: String): TrackingInformation?

    suspend fun saveTrackingItem(trackingItem: TrackingItem)

    suspend fun deleteTrackingItem(trackingItem: TrackingItem)
}