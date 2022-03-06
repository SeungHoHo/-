package fastcampus.aop.part3.aop.part4.aop_part4_chapter05.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GithubRepository")
data class GithubRepoEntity(
    val name: String,
    @PrimaryKey val fullName: String,
    @Embedded val owner: GithubOwner,
    val description: String?,
    val language: String?,
    val updatedAt: String,
    val stargazersCount: Int
)