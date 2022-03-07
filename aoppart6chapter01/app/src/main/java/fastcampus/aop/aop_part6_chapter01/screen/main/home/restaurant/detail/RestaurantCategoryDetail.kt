package fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail

import androidx.annotation.StringRes
import fastcampus.aop.aop_part6_chapter01.R

enum class RestaurantCategoryDetail(
    @StringRes val categoryNameId: Int
) {

    MENU(R.string.menu), REVIEW(R.string.review)

}