package fastcampus.aop.aop_part5_chapter07.presentation.mypage

import fastcampus.aop.aop_part5_chapter07.domain.model.ReviewedMovie
import fastcampus.aop.aop_part5_chapter07.presentation.BasePresenter
import fastcampus.aop.aop_part5_chapter07.presentation.BaseView

interface MyPageContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription(message: String)

        fun showErrorDescription(message: String)

        fun showReviewedMovies(reviewedMovies: List<ReviewedMovie>)
    }

    interface Presenter : BasePresenter
}