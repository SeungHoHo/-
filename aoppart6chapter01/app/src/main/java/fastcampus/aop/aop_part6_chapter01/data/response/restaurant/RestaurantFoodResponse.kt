package fastcampus.aop.aop_part6_chapter01.data.response.restaurant

import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantFoodEntity

data class RestaurantFoodResponse(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String
) {

    fun toEntity(restaurantId: Long, restaurantTitle: String) = RestaurantFoodEntity(
        id,
        title,
        description,
        price,
        imageUrl,
        restaurantId,
        restaurantTitle
    )


}
