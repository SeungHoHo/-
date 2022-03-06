package fastcampus.aop.part3.aop.part4.aop_part4_chapter05.data.response

import fastcampus.aop.part3.aop.part4.aop_part4_chapter05.data.entity.GithubRepoEntity

data class GithubRepoSearchResponse(
    val totalCount: Int,
    val items: List<GithubRepoEntity>
)