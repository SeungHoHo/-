package fastcampus.aop.aop_part5_chapter07.domain.usecase

import fastcampus.aop.aop_part5_chapter07.data.repository.ReviewRepository
import fastcampus.aop.aop_part5_chapter07.domain.model.Review

class DeleteReviewUseCase(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(review: Review) =
        reviewRepository.removeReview(review)
}