package fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackingitems

import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingInformation
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingItem
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.BasePresenter
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.BaseView

class TrackingItemsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription()

        fun showTrackingItemInformation(trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>)
    }

    interface Presenter : BasePresenter {

        var trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>

        fun refresh()
    }
}