package fastcampus.aop.aop_part5_chapter07.data.api

import fastcampus.aop.aop_part5_chapter07.domain.model.User

interface UserApi {

    suspend fun saveUser(user: User): User
}