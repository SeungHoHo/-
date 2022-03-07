package fastcampus.aop.aop_part5_chapter06_repeat.data.repository

import fastcampus.aop.aop_part5_chapter06_repeat.data.api.SweetTrackerApi
import fastcampus.aop.aop_part5_chapter06_repeat.data.db.TrackingItemDao
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingInformation
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class TrackingItemRepositoryImpl(
    private val trackerApi: SweetTrackerApi,
    private val trackingItemDao: TrackingItemDao,
    private val dispatcher: CoroutineDispatcher
) : TrackingItemRepository {

    override val trackingItems: Flow<List<TrackingItem>> =
        trackingItemDao.allTrackingItems()
            .distinctUntilChanged()
            .flowOn(dispatcher)

    override suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>> = withContext(dispatcher) {
        trackingItemDao.getAll()
            .mapNotNull { trackingItem ->
                val relatedTrackingInfo = trackerApi.getTrackingInformation(
                    trackingItem.company.code,
                    trackingItem.invoice
                ).body()?.sortTrackingDetailsByTimeDescending()

                if (!relatedTrackingInfo!!.errorMessage.isNullOrBlank()) {
                    null
                } else {
                    trackingItem to relatedTrackingInfo
                }
            }
            .sortedWith(
                compareBy(
                    { it.second.level },
                    { - (it.second.lastDetail?.time ?: Long.MAX_VALUE) }
                )
            )
    }

    override suspend fun getTrackingInformation(companyCode: String, invoice: String): TrackingInformation? =
        trackerApi.getTrackingInformation(companyCode, invoice)
            .body()
            ?.sortTrackingDetailsByTimeDescending()


    override suspend fun saveTrackingItem(trackingItem: TrackingItem) = withContext(dispatcher) {
        val trackingInformation = trackerApi.getTrackingInformation(
            trackingItem.company.code,
            trackingItem.invoice
        ).body()

        if (!trackingInformation!!.errorMessage.isNullOrBlank()) {
            throw RuntimeException(trackingInformation.errorMessage)
        }

        trackingItemDao.insert(trackingItem)
    }

    override suspend fun deleteTrackingItem(trackingItem: TrackingItem) {
        trackingItemDao.delete(trackingItem)
    }

    private fun TrackingInformation.sortTrackingDetailsByTimeDescending() =
        copy(trackingDetails = trackingDetails?.sortedByDescending { it.time ?: 0L })
}