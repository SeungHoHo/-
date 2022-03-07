package fastcampus.aop.aop_part5_chapter07.data.repository

import fastcampus.aop.aop_part5_chapter07.domain.model.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>

    suspend fun getMovies(movieIds: List<String>): List<Movie>
}