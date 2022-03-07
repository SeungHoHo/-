package fastcampus.aop.aop_part5_chapter07.domain.usecase

import fastcampus.aop.aop_part5_chapter07.data.repository.ReviewRepository
import fastcampus.aop.aop_part5_chapter07.data.repository.UserRepository
import fastcampus.aop.aop_part5_chapter07.domain.model.MovieReviews
import fastcampus.aop.aop_part5_chapter07.domain.model.User

class GetAllMovieReviewsUseCase(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(movieId: String): MovieReviews {
        val reviews = reviewRepository.getAllMovieReviews(movieId)
        val user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())

            return MovieReviews(null, reviews)
        }

        return MovieReviews(
            reviews.find { it.userId == user.id },
            reviews.filter { it.userId != user.id }
        )
    }
}