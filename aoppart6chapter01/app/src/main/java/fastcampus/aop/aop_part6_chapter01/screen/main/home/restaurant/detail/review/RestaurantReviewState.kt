package fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.review

import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantReviewEntity
import fastcampus.aop.aop_part6_chapter01.model.restaurant.review.RestaurantReviewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.RestaurantDetailState

sealed class RestaurantReviewState {

    object Uninitialized: RestaurantReviewState()

    object Loading: RestaurantReviewState()

    data class Success(
        val reviewList: List<RestaurantReviewModel>
    ): RestaurantReviewState()

}
