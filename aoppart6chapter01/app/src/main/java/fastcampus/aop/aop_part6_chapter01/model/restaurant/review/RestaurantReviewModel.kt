package fastcampus.aop.aop_part6_chapter01.model.restaurant.review

import android.net.Uri
import fastcampus.aop.aop_part6_chapter01.model.CellType
import fastcampus.aop.aop_part6_chapter01.model.Model

data class RestaurantReviewModel (
    override val id: Long,
    override val type: CellType = CellType.REVIEW_CELL,
    val title: String,
    val description: String,
    val grade: Float,
    val thumbnailImageUri: Uri? = null
): Model(id, type)