package fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackinghistory

import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingInformation
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingItem
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.BasePresenter
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.BaseView

class TrackingHistoryContract {

    interface View : BaseView<Presenter> {

        fun hideLoadingIndicator()

        fun showTrackingItemInformation(trackingItem: TrackingItem, trackingInformation: TrackingInformation)

        fun finish()
    }

    interface Presenter : BasePresenter {

        fun refresh()

        fun deleteTrackingItem()
    }
}