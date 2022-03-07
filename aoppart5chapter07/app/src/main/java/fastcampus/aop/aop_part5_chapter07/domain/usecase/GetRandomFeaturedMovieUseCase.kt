package fastcampus.aop.aop_part5_chapter07.domain.usecase

import fastcampus.aop.aop_part5_chapter07.data.repository.MovieRepository
import fastcampus.aop.aop_part5_chapter07.data.repository.ReviewRepository
import fastcampus.aop.aop_part5_chapter07.domain.model.FeaturedMovie

class GetRandomFeaturedMovieUseCase(
    private val movieRepository: MovieRepository,
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(): FeaturedMovie? {
        val featuredMovies = movieRepository.getAllMovies()
            .filter { it.id.isNullOrBlank().not() }
            .filter { it.isFeatured == true }

        if (featuredMovies.isNullOrEmpty()) {
            return null
        }

        return featuredMovies.random()
            .let { movie ->
                val latestReview = reviewRepository.getLatestReview(movie.id!!)
                FeaturedMovie(movie, latestReview)
            }
    }
}