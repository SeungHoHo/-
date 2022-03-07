package fastcampus.aop.aop_part5_chapter06_repeat.work

import androidx.work.DelegatingWorkerFactory
import fastcampus.aop.aop_part5_chapter06_repeat.data.repository.TrackingItemRepository
import kotlinx.coroutines.CoroutineDispatcher

class AppWorkerFactory(
    trackingItemRepository: TrackingItemRepository,
    dispatcher: CoroutineDispatcher
) : DelegatingWorkerFactory() {

    init {
        addFactory(TrackingCheckWorkerFactory(trackingItemRepository, dispatcher))
    }
}