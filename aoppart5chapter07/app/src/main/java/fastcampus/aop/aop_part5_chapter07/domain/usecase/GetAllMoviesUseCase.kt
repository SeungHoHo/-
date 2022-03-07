package fastcampus.aop.aop_part5_chapter07.domain.usecase

import fastcampus.aop.aop_part5_chapter07.data.repository.MovieRepository
import fastcampus.aop.aop_part5_chapter07.domain.model.Movie

class GetAllMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> = movieRepository.getAllMovies()
}