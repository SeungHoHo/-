package fastcampus.aop.aop_part5_chapter07.data.repository

import fastcampus.aop.aop_part5_chapter07.domain.model.User

interface UserRepository {

    suspend fun getUser(): User?

    suspend fun saveUser(user: User)
}