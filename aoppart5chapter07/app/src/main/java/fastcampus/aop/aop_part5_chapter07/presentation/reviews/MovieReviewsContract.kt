package fastcampus.aop.aop_part5_chapter07.presentation.reviews

import fastcampus.aop.aop_part5_chapter07.domain.model.Movie
import fastcampus.aop.aop_part5_chapter07.domain.model.MovieReviews
import fastcampus.aop.aop_part5_chapter07.domain.model.Review
import fastcampus.aop.aop_part5_chapter07.presentation.BasePresenter
import fastcampus.aop.aop_part5_chapter07.presentation.BaseView

interface MovieReviewsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovieInformation(movie: Movie)

        fun showReviews(reviews: MovieReviews)

        fun showErrorToast(message: String)
    }

    interface Presenter : BasePresenter {

        val movie: Movie

        fun requestAddReview(content: String, score: Float)

        fun requestRemoveReview(review: Review)
    }
}