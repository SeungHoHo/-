package fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.review

interface RestaurantReviewRepository {

    suspend fun getReviews(restaurantTitle: String): DefaultRestaurantReviewRepository.Result


}