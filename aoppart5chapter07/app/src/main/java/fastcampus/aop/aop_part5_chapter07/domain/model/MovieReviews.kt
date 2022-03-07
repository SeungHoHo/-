package fastcampus.aop.aop_part5_chapter07.domain.model

data class MovieReviews(
    val myReview: Review?,
    val othersReview: List<Review>
)